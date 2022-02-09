package ru.ogon.sdk.handlers

import ru.ogon.sdk.model.MobileEvent
import ru.ogon.sdk.model.MobileEventType

class DefaultGooglePayEventHandler(private val dispatcher: MobileEventDispatcher) : MobileEventHandler {
    override fun handle(event: MobileEvent): Boolean = event.takeIf {
        it.type == MobileEventType.MOBILE_EVENT_GOOGLEPAY_IS_READY_TO_PAY_REQUEST
    }?.let {
        val result = MobileEvent.newBuilder().run {
            type = MobileEventType.MOBILE_EVENT_GOOGLEPAY_IS_READY_TO_PAY_RESPONSE
            isReadyToPay = false
            build()
        }

        dispatcher.send(result)

        true
    } ?: false
}