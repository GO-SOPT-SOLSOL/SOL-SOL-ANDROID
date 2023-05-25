package com.solosol.solsolandroid.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyAccountResponse(
    @SerialName("data")
    val data: Data? = null,
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
        val id: Int,
            @SerialName("memberId")
        val memberId: Int,
            @SerialName("bank")
        val bank: String,
            @SerialName("accountNumber")
        val accountNumber: String,
            @SerialName("balance")
        val balance: Int,
            @SerialName("kind")
        val kind: String,
            @SerialName("name")
        val name: String,
    )
}