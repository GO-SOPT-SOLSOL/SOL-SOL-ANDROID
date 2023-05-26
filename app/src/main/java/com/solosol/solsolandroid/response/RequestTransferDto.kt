package com.solosol.solsolandroid.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestTransferDto(
    @SerialName("senderAccountsId")
    val senderAccountsId: Int,
    @SerialName("price")
    val price: Long,
    @SerialName("bank")
    val bank: String,
    @SerialName("number")
    val number: String,
    @SerialName("transferMemo")
    val transferMemo: String?,
    @SerialName("receiverMemo")
    val receiverMemo: String?,
    @SerialName("charge")
    val charge: Int
)