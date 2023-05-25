package com.solosol.solsolandroid.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountInfoResponse(
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: Int? = null,
    @SerialName("success")
    val success: Boolean? = null
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Integer,
        @SerialName("memberId")
        val memberId: Integer,
        @SerialName("bank")
        val bank: String,
        @SerialName("accountNumber")
        val accountNumber: String,
        @SerialName("balance")
        val balance: Integer,
        @SerialName("kind")
        val kind: String,
        @SerialName("name")
        val name: String,
    )
}