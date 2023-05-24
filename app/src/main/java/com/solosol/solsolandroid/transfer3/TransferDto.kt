package com.solosol.solsolandroid.transfer3

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestTransferDto(
    @SerialName("senderAccountsId")
    val senderAccountsId: Int,
    @SerialName("price")
    val price: Int,
    @SerialName("bank")
    val bank: String,
    @SerialName("number")
    val number: String,
    @SerialName("transferMemo")
    val transferMemo: String,
    @SerialName("receiverMemo")
    val receiverMemo: String,
    @SerialName("charge")
    val charge: Int,
)

@Serializable
data class ResponseTransferDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
)