package ru.ogon.sdk

import android.os.Bundle

interface ApplicationAdapter {
    companion object {
        const val USER_ID = "USER_ID"
        const val DEVICE_ID = "DEVICE_ID"
        const val CONFIRMATION_ID = "CONFIRMATION_ID"
        const val PASSWORD_ENABLED = "PASSWORD_ENABLED"
        const val BIOMETRY_ENABLED = "BIOMETRY_ENABLED"
    }

    fun getParams(): Bundle
    fun setParams(params: Bundle)
    fun createKeys(password: String): String
}