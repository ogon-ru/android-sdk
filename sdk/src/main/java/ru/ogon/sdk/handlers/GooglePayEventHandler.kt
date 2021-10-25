package ru.ogon.sdk.handlers

import android.util.Log
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status
import com.google.android.gms.wallet.PaymentData
import ru.ogon.sdk.fromJson
import ru.ogon.sdk.model.IsReadyToPayRequest
import ru.ogon.sdk.model.MobileEvent
import ru.ogon.sdk.model.MobileEventType
import ru.ogon.sdk.model.PaymentDataRequest
import ru.ogon.sdk.toJson

class GooglePayEventHandler(
    private val contract: GooglePayContract,
    private val dispatcher: MobileEventDispatcher,
) : MobileEventHandler {

    override fun handle(event: MobileEvent): Boolean = when (event.type) {
        MobileEventType.MOBILE_EVENT_GOOGLEPAY_IS_READY_TO_PAY_REQUEST -> {
            isReadyToPay(event.isReadyToPayRequest)
            true
        }
        MobileEventType.MOBILE_EVENT_GOOGLEPAY_PAYMENT_DATA_REQUEST -> {
            loadPaymentData(event.paymentDataRequest)
            true
        }
        else -> false
    }

    fun onPaymentSuccess(paymentData: PaymentData) {
        val event = MobileEvent.newBuilder().run {
            type = MobileEventType.MOBILE_EVENT_GOOGLEPAY_PAYMENT_DATA_RESPONSE
            paymentDataBuilder.fromJson(paymentData.toJson())
            build()
        }

        dispatcher.send(event)
    }

    fun onPaymentError(status: Status) {
        val event = MobileEvent.newBuilder().run {
            type = MobileEventType.MOBILE_EVENT_GOOGLEPAY_PAYMENT_DATA_ERROR
            errorBuilder.apply {
                code = status.statusCode
                message = status.statusMessage ?: ""
            }
            build()
        }

        dispatcher.send(event)
    }

    private fun isReadyToPay(payload: IsReadyToPayRequest) {
        val json = payload.toJson()
        val request = com.google.android.gms.wallet.IsReadyToPayRequest.fromJson(json)

        contract.isReadyToPay(request).addOnCompleteListener { task ->
            val result = try {
                task.getResult(ApiException::class.java) ?: false
            } catch (exception: ApiException) {
                Log.w("[OgonWidget]", exception)
                false
            }


            val event = MobileEvent.newBuilder().run {
                type = MobileEventType.MOBILE_EVENT_GOOGLEPAY_IS_READY_TO_PAY_RESPONSE
                isReadyToPay = result
                build()
            }

            dispatcher.send(event)
        }
    }

    private fun loadPaymentData(payload: PaymentDataRequest) {
        val json = payload.toJson()
        val request = com.google.android.gms.wallet.PaymentDataRequest.fromJson(json)

        contract.loadPaymentData(request)
    }
}