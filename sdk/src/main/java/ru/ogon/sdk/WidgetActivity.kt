package ru.ogon.sdk

import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.Rect
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import ru.ogon.sdk.handlers.*
import ru.ogon.sdk.model.MobileEvent
import ru.ogon.sdk.model.MobileEventType
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


private const val BASE_URL = BuildConfig.BASE_URL
private const val RECORD_AUDIO_REQUEST_CODE = 1
private const val INPUT_FILE_REQUEST_CODE = 3
private const val CAMERA_REQUEST_CODE = 4
private const val RESOURCE_AUDIO_CAPTURE = "android.webkit.resource.AUDIO_CAPTURE"

open class WidgetActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TOKEN = "EXTRA_TOKEN"
        const val EXTRA_BASE_URL = "EXTRA_BASE_URL"
        const val EXTRA_HTTP_USERNAME = "EXTRA_HTTP_USERNAME"
        const val EXTRA_HTTP_PASSWORD = "EXTRA_HTTP_PASSWORD"
        const val EXTRA_QUERY_PARAMS = "EXTRA_QUERY_PARAMS"

        const val RESULT_ERROR = 100
    }

    protected val handlers = mutableListOf<MobileEventHandler>()
    protected lateinit var webView: WebView
    protected lateinit var jsBridge: JSBridge
    protected lateinit var baseUrl: String
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

        webView = findViewById<WebView>(R.id.web_view).apply {
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

        val url = Uri.parse(baseUrl).buildUpon().run {
            if (!token.isNullOrBlank()) {
                appendQueryParameter("token", token)
            }

            (intent.getSerializableExtra(EXTRA_QUERY_PARAMS) as? HashMap<String, String>)
                ?.forEach {
                    appendQueryParameter(it.key.lowercase(), it.value)
                }

            toString()
        }

        webView.loadUrl(url)
        setResult(RESULT_OK)

        if (BuildConfig.DEBUG) {
            Log.d("[OgonWidget]", "init; url: $url;")
        }

        val rootView = window.decorView.findViewById<View>(android.R.id.content)
        val containerView = (webView.parent as View)

        // GBoard autofill workaround
        rootView?.viewTreeObserver?.addOnGlobalLayoutListener {
            try {
                val rect = Rect().apply {
                    window.decorView.getWindowVisibleDisplayFrame(this)
                }

                containerView.layoutParams = containerView.layoutParams.apply {
                    height = rect.height()
                }
            } catch (e: Throwable) {
                Log.e("[OgonWidget]", "Container resize error", e)
            }
        }

        handlers.add(OpenUrlEventHandler(this))
        handlers.add(ShareUrlEventHandler(this))
        handlers.add(ClipboardWriteEventHandler(this))
        handlers.add(DefaultGooglePayEventHandler(jsBridge))
    }

    override fun onKeyDown(keyCode: Int, keyEvent: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isWidgetUrl(webView.url) && !httpError) {
                val event = MobileEvent.newBuilder().run {
                    type = MobileEventType.MOBILE_EVENT_BACK
                    build()
                }

                jsBridge.send(event)
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

    private fun handleEvent(event: MobileEvent) {
        handlers.find {
            it.handle(event)
        } ?: Log.i("[OgonWidget]", "Unhandled event type: ${event.type}")
    }

    private fun handleNavigation() {
        webView.post {
            if (webView.url?.endsWith("/escape") == true) {
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
                Log.e("[OgonWidget]", "Unable to create Image File", ex)
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

    private fun isWidgetUrl(url: String?): Boolean = url?.let {
        it.startsWith(baseUrl, true) || it.contains("widget.ogon.ru", true)
    } ?: false

    private inner class WidgetWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            if (BuildConfig.DEBUG) {
                Log.d("[OgonWidget]", "onPageStarted; url: $url")
            }

            if (isWidgetUrl(url)) {
                jsBridge.injectJsCode()
            }
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            if (httpError && view?.canGoBack() != true && callingActivity != null) {
                setResult(RESULT_ERROR)
                finish()
            } else {
                super.onPageFinished(view, url)

                if (isWidgetUrl(url)) {
                    jsBridge.injectJsCode()
                }
            }
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            if (BuildConfig.DEBUG) { // handle local ssl
                handler?.proceed()
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