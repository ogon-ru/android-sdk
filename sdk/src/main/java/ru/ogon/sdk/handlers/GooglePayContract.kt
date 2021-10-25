package ru.ogon.sdk.handlers

import com.google.android.gms.tasks.Task
import com.google.android.gms.wallet.IsReadyToPayRequest
import com.google.android.gms.wallet.PaymentDataRequest

interface GooglePayContract {
    fun isReadyToPay(request: IsReadyToPayRequest): Task<Boolean>
    fun loadPaymentData(request: PaymentDataRequest): Unit
}