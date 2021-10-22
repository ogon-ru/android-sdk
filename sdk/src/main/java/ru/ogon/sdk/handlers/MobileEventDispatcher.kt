package ru.ogon.sdk.handlers

import ru.ogon.sdk.model.MobileEvent

interface MobileEventDispatcher {
    fun send(event: MobileEvent)
}