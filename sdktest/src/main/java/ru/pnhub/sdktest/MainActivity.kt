package ru.pnhub.sdktest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import ru.pnhub.widgetsdk.WidgetActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJHUEIgRWNvc3lzdGVtIiwiZXhwIjo0NzY1NDI2NjcwLCJpYXQiOjE2MTE4MjY2NzAsImlzcyI6IkdQQiIsInVpZCI6MzI0LCJ1Z3AiOjEsInN0cCI6MH0.UTot-Qqfc2-jqF6D436fIt7Idt0x6-xHw8BGjSVOaI2SMzafn5HhHOCXaIdzkTIcvj---G7JtUL5awbYQuncLGVCYHDLoEZhpdz9-4PqrlLi839x4aK_Cg74OuZuuYKzffe7exGLcQqi-OsJqIIEVh3BBFaB_F2opSi43wdpj0r9X767H0WHpvUUpIKpsLIpc7_nm-UrnET3pmRHfx128SGlqvk8X7_vE1Hfo439SgtHX-uBaJt8cmUQNnl-DYaAcSt0K4IrDRPTpYSbocxe9vO5c9kGabKrQp9a73L5irGtPDvigRQ6Do99rwY_ZAhO0iXUuDdYb4x0TkS2VI717A"

        findViewById<Button>(R.id.button).apply {
            setOnClickListener {
                val intent = Intent(this@MainActivity, WidgetActivity::class.java).apply {
                    putExtra(WidgetActivity.EXTRA_TOKEN, token)
                    putExtra(WidgetActivity.EXTRA_BASE_URL, "https://widget.setpartnerstv.com")
//                    putExtra(WidgetActivity.EXTRA_BASE_URL, "https://192.168.31.80:4305")
                }

                startActivity(intent)
            }
        }

        WebView.setWebContentsDebuggingEnabled(true)
    }
}