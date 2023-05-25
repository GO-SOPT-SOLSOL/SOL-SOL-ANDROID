package com.solosol.solsolandroid.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTransferDto(
        @SerialName("status")
        val status: Int,
        @SerialName("success")
        val success: Boolean,
        @SerialName("message")
        val message: String,
)