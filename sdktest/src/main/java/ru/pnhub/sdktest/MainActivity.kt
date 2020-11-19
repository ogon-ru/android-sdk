package ru.pnhub.sdktest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ru.pnhub.widgetsdk.WidgetActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJHUEIgRWNvc3lzdGVtIiwidWlkIjoxLCJpc3MiOiJHUEIiLCJleHAiOjE2MTA3NDQ0MDAsImlhdCI6MTYwMjE1NzQzMiwic3RwIjoxLCJ1Z3AiOjF9.AD39LqK90armvlffvZQrm5WPSIWPWLdpjnsTX5oUGRN3LckP9Esg9ZUgCeeMRYpkPDUu-1nVw0G0XGN92g8h3wYGVAYEiLsbWQIwPafHNEZGEXL5jKXm-yuPTBKS5cQCak3PIHDb-eH9zGZ8FCgtGRN0MnS-CqlsWzV55qG-WMvJJDaAefcL6CH1QcX_9LRBrJFLR7He7RA6Ju6e-nX6_5p2yO0hN4QHb-2SsRFMMsxa4GZGP63Tlp3N7LZQDefmieQHa8T_tn_uf2DiVUHp9-YRwsxC1l6f-y6rCe2l557KoSsLxVK5xTCa5MLaFnfCcfy_oShoB3JLdY8Ev3O-lg"

        findViewById<Button>(R.id.button).apply {
            setOnClickListener {
                val intent = Intent(this@MainActivity, WidgetActivity::class.java).apply {
                    putExtra(WidgetActivity.EXTRA_TOKEN, token)
                }

                startActivity(intent)
            }
        }
    }
}