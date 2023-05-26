package com.solosol.solsolandroid.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransferDeleteResponse(
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: Int? = null,
    @SerialName("success")
    val success: Boolean? = null
)