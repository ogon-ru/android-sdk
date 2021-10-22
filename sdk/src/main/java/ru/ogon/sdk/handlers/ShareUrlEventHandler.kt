package ru.ogon.sdk.handlers

import android.content.Context
import android.content.Intent
import ru.ogon.sdk.model.MobileEvent
import ru.ogon.sdk.model.MobileEventType

class ShareUrlEventHandler(private val context: Context) : MobileEventHandler {
    override fun handle(event: MobileEvent): Boolean = event.takeIf {
        it.type == MobileEventType.MOBILE_EVENT_SHARE_URL_REQUEST
    }?.let {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, it.shareUrlRequest)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)

        true
    } ?: false
}