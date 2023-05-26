package com.solosol.solsolandroid.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyAccountListResponse(
    @SerialName("data")
    val `data`: List<Data?>? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: Int? = null,
    @SerialName("success")
    val success: Boolean? = null
) {
    @Serializable
    data class Data(
            @SerialName("accountNumber")
            val accountNumber: String? = null,
            @SerialName("balance")
            val balance: Long? = null,
            @SerialName("bank")
            val bank: String? = null,
            @SerialName("id")
            val id: Int? = null,
            @SerialName("memberId")
            val memberId: Int? = null,
            @SerialName("name")
            val name: String? = null,
            @SerialName("kind")
            val kind: String? = null,
    )
}