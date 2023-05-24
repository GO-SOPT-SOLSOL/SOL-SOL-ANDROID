package com.solosol.solsolandroid.transfer3

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseAccountDto(
    @SerialName("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<AccountInfo>
) {
    @Serializable
    data class AccountInfo(
        @SerialName("id")
        val id: Int,
        @SerialName("memberId")
        val memberId: Int,
        @SerialName("name")
        val name: String,
        @SerialName("bank")
        val bank: String,
        @SerialName("accountNumber")
        val accountNumber: String,
        @SerialName("balance")
        val balance: Int,
    )
}