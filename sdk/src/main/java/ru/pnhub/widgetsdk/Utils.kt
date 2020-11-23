package ru.pnhub.widgetsdk

import com.google.protobuf.Message
import com.google.protobuf.MessageOrBuilder
import com.google.protobuf.util.JsonFormat
import ru.pnhub.widgetsdk.model.IsReadyToPayRequest
import ru.pnhub.widgetsdk.model.PaymentDataRequest

fun MessageOrBuilder.toJson(preservingProtoFieldNames: Boolean = false): String {
    // "apiVersionMinor": 0 workaround
    val fields = setOf(
            IsReadyToPayRequest.getDescriptor().findFieldByNumber(IsReadyToPayRequest.API_VERSION_MINOR_FIELD_NUMBER),
            PaymentDataRequest.getDescriptor().findFieldByNumber(PaymentDataRequest.API_VERSION_MINOR_FIELD_NUMBER),
    )
    var printer = JsonFormat.printer().omittingInsignificantWhitespace().includingDefaultValueFields(fields)
    printer = if (preservingProtoFieldNames) printer.preservingProtoFieldNames() else printer

    return printer.print(this)
}

fun <T> T.fromJson(json: String) where T : Message.Builder {
    JsonFormat.parser().ignoringUnknownFields().merge(json, this)
}
