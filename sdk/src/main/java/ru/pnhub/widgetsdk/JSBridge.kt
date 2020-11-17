package ru.pnhub.widgetsdk

import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import ru.pnhub.widgetsdk.model.MobileEvent

typealias EventListener = (MobileEvent) -> Unit

internal const val INJECT_JS_CODE = """
    javascript:(function (){
        window.PNWidget._listeners = new Set();
    
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
        val json = event.toJson()
        val js = """javascript:(function() {
            const event = $json;
            for (let listener of window.PNWidget._listeners.values()) {
                listener(event);
            }
        })()""".trimMargin()

        webView.loadUrl(js)
    }

    private fun handleEvent(json: String) {
        try {
            val event = MobileEvent.newBuilder().run {
                fromJson(json)
                build()
            }

            eventListener(event)
        } catch (exception: Throwable) {
            Log.e("[SDKWidget]", "Mobile event parse error", exception)
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