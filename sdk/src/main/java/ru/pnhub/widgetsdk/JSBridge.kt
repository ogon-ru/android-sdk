package ru.pnhub.widgetsdk

import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import ru.pnhub.widgetsdk.model.MobileEvent

typealias EventListener = (MobileEvent) -> Unit

internal const val INJECT_JS_CODE = """
    (function (){
        if (window.PNWidget._listeners) {
            return;
        }
        
        window.PNWidget._listeners = new Set();
        window.PNWidget.version = "${BuildConfig.VERSION}";
    
        window.PNWidget.sendMobileEvent = function sendMobileEvent(event) {
            window.PNWidget._sendMobileEvent(JSON.stringify(event));
        };
        
        window.PNWidget.onMobileEvent = function onMobileEvent(listener) {
            window.PNWidget._listeners.add(listener);
        
            return function unsubscribe() {
                window.PNWidget._listeners.delete(listener);
            };
        };
        
        function wrap(fn) {
            return function wrapper() {
                var res = fn.apply(this, arguments);
                window.PNWidget._navigationStateChange();
                return res;
            }
        }

        history.pushState = wrap(history.pushState);
        history.replaceState = wrap(history.replaceState);
        window.addEventListener('popstate', function() {
            window.PNWidget._navigationStateChange();
        });
        
        if (window.PNWidget.onready) {
            window.PNWidget.onready();
        }
    })();
"""

class JSBridge(
        private val webView: WebView,
        private val eventListener: EventListener,
        private val navigationStateChange: () -> Unit,
) {

    init {
        webView.addJavascriptInterface(JSInterface(), "PNWidget")
    }

    fun sendEvent(event: MobileEvent) {
        val json = event.toJson(
            preservingProtoFieldNames = true,
        )
        val js = """(function() {
            const event = $json;
            for (let listener of window.PNWidget._listeners.values()) {
                listener(event);
            }
        })()""".trimMargin()

        webView.post {
            webView.evaluateJavascript(js, null)
        }

        if (BuildConfig.DEBUG) {
            Log.d("[OgonWidget]", "sendEvent; json: $json;")
        }
    }

    fun injectJsCode() {
        try {
            webView.evaluateJavascript(INJECT_JS_CODE, null)
        } catch (exception: Throwable) {
            Log.e("[OgonWidget]", "JS code injection failed", exception)
        }

        if (BuildConfig.DEBUG) {
            Log.d("[OgonWidget]", "injectJsCode;")
        }
    }

    private fun handleEvent(json: String) {
        try {
            val event = MobileEvent.newBuilder().run {
                fromJson(json)
                build()
            }

            eventListener(event)

            if (BuildConfig.DEBUG) {
                Log.d("[OgonWidget]", "handleEvent; json: $json;")
            }
        } catch (exception: Throwable) {
            Log.e("[OgonWidget]", "Mobile event parse error", exception)
        }
    }

    private inner class JSInterface {
        @JavascriptInterface
        fun _sendMobileEvent(json: String) {
            handleEvent(json)
        }

        @JavascriptInterface
        fun _navigationStateChange() {
            navigationStateChange()
        }
    }
}