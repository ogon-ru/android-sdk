package ru.pnhub.sdktest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import ru.pnhub.widgetsdk.WidgetActivity

private const val BASE_URL = BuildConfig.BASE_URL
private const val TOKEN = BuildConfig.TOKEN

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).apply {
            setOnClickListener {
                val intent = Intent(this@MainActivity, WidgetActivity::class.java).apply {
                    putExtra(WidgetActivity.EXTRA_TOKEN, TOKEN)
                    putExtra(WidgetActivity.EXTRA_BASE_URL, BASE_URL)
                }

                startActivity(intent)
            }
        }

        WebView.setWebContentsDebuggingEnabled(true)
    }
}