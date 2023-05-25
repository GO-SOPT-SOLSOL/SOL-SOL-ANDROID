package com.solosol.solsolandroid.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecentTransferResponse(
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
        @SerialName("transfers")
        val transfers: List<Transfer?>?
    ) {
        @Serializable
        data class Transfer(
            @SerialName("id")
            val id: Int,
            @SerialName("accountsId")
            val accountsId: Int,
            @SerialName("price")
            val price: Int,
            @SerialName("name")
            val name: String,
            @SerialName("bank")
            val bank: String,
            @SerialName("accountNumber")
            val accountNumber: String,
            @SerialName("createdAt")
            val createdAt: String,
        )
    }
}