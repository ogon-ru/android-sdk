package ru.ogon.sdk

import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import ru.ogon.sdk.handlers.MobileEventDispatcher
import ru.ogon.sdk.model.MobileEvent

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
            window.PNWidget._onListener();
        
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
) : MobileEventDispatcher {

    private var hasListeners = false
    private val eventQueue = mutableListOf<MobileEvent>()

    init {
        webView.addJavascriptInterface(JSInterface(), "PNWidget")
    }

    override fun send(event: MobileEvent) {
        if (!hasListeners) {
            eventQueue.add(event)
            return
        }

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

    private fun onListener() {
        hasListeners = true
        eventQueue.forEach { send(it) }
        eventQueue.clear()
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

        @JavascriptInterface
        fun _onListener() {
            onListener()
        }
    }
}