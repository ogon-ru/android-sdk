package ru.ogon.sdk.handlers

import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.ogon.sdk.model.MobileEvent
import ru.ogon.sdk.model.MobileEventType

class OpenUrlEventHandler(private val context: Context) : MobileEventHandler {
    override fun handle(event: MobileEvent): Boolean = event.takeIf {
        it.type == MobileEventType.MOBILE_EVENT_OPEN_URL_REQUEST
    }?.let {
        val uri = Uri.parse(it.openUrlRequest)
        val intent = when(uri.scheme) {
            "mailto" -> Intent(Intent.ACTION_SENDTO, uri)
            else -> Intent(Intent.ACTION_VIEW, uri)
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }

        true
    } ?: false
}