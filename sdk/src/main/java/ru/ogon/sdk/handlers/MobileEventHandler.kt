package ru.ogon.sdk.handlers

import ru.ogon.sdk.model.MobileEvent

interface MobileEventHandler {
    fun handle(event: MobileEvent): Boolean
}