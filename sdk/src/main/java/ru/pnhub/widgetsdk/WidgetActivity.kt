package ru.pnhub.widgetsdk

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status
import com.google.android.gms.wallet.*
import ru.pnhub.widgetsdk.model.MobileEvent
import ru.pnhub.widgetsdk.model.MobileEventType
import ru.pnhub.widgetsdk.model.IsReadyToPayRequest as IsReadyToPayRequestModel
import ru.pnhub.widgetsdk.model.PaymentDataRequest as PaymentDataRequestModel

private const val BASE_URL = BuildConfig.BASE_URL
private const val RECORD_AUDIO_REQUEST_CODE = 1
private const val LOAD_PAYMENT_DATA_REQUEST_CODE = 2
private const val RESOURCE_AUDIO_CAPTURE = "android.webkit.resource.AUDIO_CAPTURE"

class WidgetActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TOKEN = "EXTRA_TOKEN"

        init {
            if (BuildConfig.DEBUG) {
                WebView.setWebContentsDebuggingEnabled(true)
            }
        }
    }

    private lateinit var webView: WebView
    private lateinit var jsBridge: JSBridge
    private var permissionRequest: PermissionRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)

        val token = intent.getStringExtra(EXTRA_TOKEN)

        webView = findViewById<WebView>(R.id.webView).apply {
            settings.javaScriptEnabled = true
            settings.allowFileAccess = true
            settings.domStorageEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true

            webViewClient = WidgetWebViewClient()
            webChromeClient = WidgetWebChromeClient()
        }
        jsBridge = JSBridge(webView, ::handleEvent)

        webView.loadUrl("$BASE_URL/?token=$token")
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_AUDIO_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionRequest?.grant(arrayOf(RESOURCE_AUDIO_CAPTURE))
                } else {
                    permissionRequest?.deny()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            LOAD_PAYMENT_DATA_REQUEST_CODE -> {
                when (resultCode) {
                    RESULT_OK ->
                        data?.let { PaymentData.getFromIntent(it)?.let(::handlePaymentSuccess) }
                    AutoResolveHelper.RESULT_ERROR ->
                        AutoResolveHelper.getStatusFromIntent(data)?.let(::handlePaymentError)
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun requestMicrophone(request: PermissionRequest) {
        requestPermissions(arrayOf(android.Manifest.permission.RECORD_AUDIO), RECORD_AUDIO_REQUEST_CODE)
        permissionRequest = request
    }

    private fun createPaymentsClient(): PaymentsClient {
        val walletOptions = Wallet.WalletOptions.Builder()
            .setEnvironment(WalletConstants.ENVIRONMENT_TEST) // TODO: payment environment
            .build()

        return Wallet.getPaymentsClient(this, walletOptions)
    }

    private fun isReadyToPay(payload: IsReadyToPayRequestModel) {
        val client = createPaymentsClient()
        val json = payload.toJson()
        val request = IsReadyToPayRequest.fromJson(json)

        client.isReadyToPay(request).addOnCompleteListener { task ->
            val result = try {
                task.getResult(ApiException::class.java) ?: false
            } catch (exception: ApiException) {
                Log.w("PNWidget", exception)
                false
            }


            val event = MobileEvent.newBuilder().run {
                type = MobileEventType.MOBILE_EVENT_GOOGLEPAY_IS_READY_TO_PAY_RESPONSE
                isReadyToPay = result
                build()
            }

            jsBridge.sendEvent(event)
        }
    }

    private fun loadPaymentData(payload: PaymentDataRequestModel) {
        val client = createPaymentsClient()
        val request = PaymentDataRequest.fromJson(payload.toJson())

        AutoResolveHelper.resolveTask(client.loadPaymentData(request), this, LOAD_PAYMENT_DATA_REQUEST_CODE)
    }

    private fun handlePaymentSuccess(paymentData: PaymentData) {
        val event = MobileEvent.newBuilder().run {
            type = MobileEventType.MOBILE_EVENT_GOOGLEPAY_PAYMENT_DATA_RESPONSE
            paymentDataBuilder.fromJson(paymentData.toJson())
            build()
        }

        jsBridge.sendEvent(event)
    }

    private fun handlePaymentError(status: Status) {
        val event = MobileEvent.newBuilder().run {
            type = MobileEventType.MOBILE_EVENT_GOOGLEPAY_PAYMENT_DATA_ERROR
            errorBuilder.apply {
                code = status.statusCode
                message = status.statusMessage ?: ""
            }
            build()
        }

        jsBridge.sendEvent(event)
    }

    private fun handleEvent(mobileEvent: MobileEvent) {
        when (mobileEvent.type) {
            MobileEventType.MOBILE_EVENT_GOOGLEPAY_IS_READY_TO_PAY_REQUEST -> {
                isReadyToPay(mobileEvent.isReadyToPayRequest)
            }
            MobileEventType.MOBILE_EVENT_GOOGLEPAY_PAYMENT_DATA_REQUEST -> {
                loadPaymentData(mobileEvent.paymentDataRequest)
            }
            else -> Log.i("[PNWidget]", "Unhandled event type: ${mobileEvent.type}")
        }
    }

    private inner class WidgetWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            view?.loadUrl(INJECT_JS_CODE)
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            if (BuildConfig.DEBUG) { // handle local ssl
                handler?.proceed();
            } else {
                super.onReceivedSslError(view, handler, error)
            }
        }
    }

    private inner class WidgetWebChromeClient : WebChromeClient() {
        override fun onPermissionRequest(request: PermissionRequest?) {
            request?.apply {
                resources.forEach { resource ->
                    when (resource) {
                        RESOURCE_AUDIO_CAPTURE -> requestMicrophone(request)
                    }
                }
            }
        }
    }
}