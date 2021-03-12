package ru.pnhub.widgetsdk

import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status
import com.google.android.gms.wallet.*
import ru.pnhub.widgetsdk.model.MobileEvent
import ru.pnhub.widgetsdk.model.MobileEventType
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import ru.pnhub.widgetsdk.model.IsReadyToPayRequest as IsReadyToPayRequestModel
import ru.pnhub.widgetsdk.model.PaymentDataRequest as PaymentDataRequestModel


private const val BASE_URL = BuildConfig.BASE_URL
private const val RECORD_AUDIO_REQUEST_CODE = 1
private const val LOAD_PAYMENT_DATA_REQUEST_CODE = 2
private const val INPUT_FILE_REQUEST_CODE = 3
private const val CAMERA_REQUEST_CODE = 4
private const val RESOURCE_AUDIO_CAPTURE = "android.webkit.resource.AUDIO_CAPTURE"

class WidgetActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TOKEN = "EXTRA_TOKEN"
        const val EXTRA_BASE_URL = "EXTRA_BASE_URL"
        const val EXTRA_HTTP_USERNAME = "EXTRA_HTTP_USERNAME"
        const val EXTRA_HTTP_PASSWORD = "EXTRA_HTTP_PASSWORD"

        const val RESULT_ERROR = 100
    }

    private lateinit var webView: WebView
    private lateinit var jsBridge: JSBridge
    private lateinit var baseUrl: String
    private var httpUsername: String? = null
    private var httpPassword: String? = null
    private var permissionRequest: PermissionRequest? = null
    private var filePathCallback: ValueCallback<Array<Uri>>? = null
    private var cameraPhotoPath: String? = null
    private var httpError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)

        val token = intent.getStringExtra(EXTRA_TOKEN)
        baseUrl = intent.getStringExtra(EXTRA_BASE_URL) ?: BASE_URL
        httpUsername = intent.getStringExtra(EXTRA_HTTP_USERNAME)
        httpPassword = intent.getStringExtra(EXTRA_HTTP_PASSWORD)

        webView = findViewById<WebView>(R.id.webView).apply {
            settings.javaScriptEnabled = true
            settings.allowContentAccess = true
            settings.allowFileAccess = true
            settings.domStorageEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true

            webViewClient = WidgetWebViewClient()
            webChromeClient = WidgetWebChromeClient()
        }
        jsBridge = JSBridge(
                webView = webView,
                eventListener = ::handleEvent,
                navigationStateChange = ::handleNavigation,
        )

        webView.loadUrl("$baseUrl/?token=$token")
        setResult(RESULT_OK)
    }

    override fun onKeyDown(keyCode: Int, keyEvent: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.url.startsWith(baseUrl, true) && !httpError) {
                val event = MobileEvent.newBuilder().run {
                    type = MobileEventType.MOBILE_EVENT_BACK
                    build()
                }

                jsBridge.sendEvent(event)
                return true
            }

            if (webView.canGoBack()) {
                webView.goBack()
                return true
            }
        }

        return super.onKeyDown(keyCode, keyEvent)
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_AUDIO_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionRequest?.grant(arrayOf(RESOURCE_AUDIO_CAPTURE))
                } else {
                    permissionRequest?.deny()
                }
            }
            CAMERA_REQUEST_CODE -> startActionChooser()
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
            INPUT_FILE_REQUEST_CODE -> {
                if (resultCode == RESULT_OK) {
                    val result = if (data == null || data.dataString == null) {
                        cameraPhotoPath?.let { arrayOf(Uri.parse(it)) }
                    } else {
                        arrayOf(Uri.parse(data.dataString))
                    }

                    filePathCallback?.onReceiveValue(result)
                } else {
                    filePathCallback?.onReceiveValue(null)
                }

                filePathCallback = null
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
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

    private fun openUrl(url: String) {
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun shareUrl(url: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun handleEvent(mobileEvent: MobileEvent) {
        when (mobileEvent.type) {
            MobileEventType.MOBILE_EVENT_GOOGLEPAY_IS_READY_TO_PAY_REQUEST -> {
                isReadyToPay(mobileEvent.isReadyToPayRequest)
            }
            MobileEventType.MOBILE_EVENT_GOOGLEPAY_PAYMENT_DATA_REQUEST -> {
                loadPaymentData(mobileEvent.paymentDataRequest)
            }
            MobileEventType.MOBILE_EVENT_OPEN_URL_REQUEST -> {
                openUrl(mobileEvent.openUrlRequest)
            }
            MobileEventType.MOBILE_EVENT_SHARE_URL_REQUEST -> {
                shareUrl(mobileEvent.shareUrlRequest)
            }
            else -> Log.i("[PNWidget]", "Unhandled event type: ${mobileEvent.type}")
        }
    }

    private fun handleNavigation() {
        webView.post {
            if (webView.url.endsWith("/escape")) {
                finish()
            }
        }
    }

   private fun getAuthority(): String = application.packageName + getString(R.string.provider_package)

    private fun startActionChooser() {
        var cameraIntent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (hasCameraPermission() && cameraIntent!!.resolveActivity(packageManager) != null) {
            // Create the File where the photo should go
            val photoFile: File? = try {
                createImageFile()
            } catch (ex: Throwable) {
                Log.e("WidgetActivity", "Unable to create Image File", ex)
                null
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                val fileUri = FileProvider.getUriForFile(this, getAuthority(), photoFile)
                cameraPhotoPath = "file:" + photoFile.absolutePath
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
//                cameraIntent.putExtra("PhotoPath", cameraPhotoPath)

                val resInfoList: List<ResolveInfo> = packageManager.queryIntentActivities(cameraIntent, PackageManager.MATCH_DEFAULT_ONLY)
                for (resolveInfo in resInfoList) {
                    val packageName = resolveInfo.activityInfo.packageName
                    grantUriPermission(packageName, fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
            } else {
                cameraIntent = null
            }
        }

        val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
        contentSelectionIntent.type = "image/*"

        val intentArray = cameraIntent?.let { arrayOf(it) } ?: emptyArray()

        val chooserIntent = Intent(Intent.ACTION_CHOOSER)
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)

        startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE)
    }

    private fun hasCameraPermission(): Boolean = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
        checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
        checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }

    private fun openFileChooser(callback: ValueCallback<Array<Uri>>?): Boolean {
        try {
            filePathCallback?.onReceiveValue(null)
            filePathCallback = callback

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasCameraPermission()) {
                val permissions = arrayOf(
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                requestPermissions(permissions, CAMERA_REQUEST_CODE)
            } else {
                startActionChooser()
            }

            return true
        } catch (e: Throwable) {
            filePathCallback = null
        }

        return false
    }

    private fun createImageFile(): File? {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(imageFileName, ".jpg", dir)
    }

    private inner class WidgetWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            view?.loadUrl(INJECT_JS_CODE)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            if (httpError && view?.canGoBack() != true && callingActivity != null) {
                setResult(RESULT_ERROR)
                finish()
            } else {
                super.onPageFinished(view, url)
            }
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            if (BuildConfig.DEBUG) { // handle local ssl
                handler?.proceed();
            } else {
                super.onReceivedSslError(view, handler, error)
            }
        }

        override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
            if (request !== null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (request.method.equals("get", true) && request.isForMainFrame) {
                    httpError = true
                }
            }

            super.onReceivedHttpError(view, request, errorResponse)
        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            if (request !== null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (request.method.equals("get", true) && request.isForMainFrame) {
                    httpError = true
                }
            }

            super.onReceivedError(view, request, error)
        }

        override fun onReceivedHttpAuthRequest(view: WebView?, handler: HttpAuthHandler?, host: String?, realm: String?) {
            if (httpUsername.isNullOrEmpty() || httpPassword.isNullOrEmpty()) {
                super.onReceivedHttpAuthRequest(view, handler, host, realm)
            } else {
                handler?.proceed(httpUsername, httpPassword)
            }
        }
    }

    private inner class WidgetWebChromeClient : WebChromeClient() {
        @TargetApi(Build.VERSION_CODES.M)
        override fun onPermissionRequest(request: PermissionRequest?) {
            request?.apply {
                resources.forEach { resource ->
                    when (resource) {
                        RESOURCE_AUDIO_CAPTURE -> requestMicrophone(request)
                    }
                }
            }
        }

        override fun onShowFileChooser(webView: WebView?, callback: ValueCallback<Array<Uri>>?, params: FileChooserParams?): Boolean {
            return openFileChooser(callback)
        }

    }
}