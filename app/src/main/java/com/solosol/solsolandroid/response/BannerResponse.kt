package com.solosol.solsolandroid.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerResponse(
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
        @SerialName("content")
        val content: String? = null,
        @SerialName("id")
        val id: Int? = null,
        @SerialName("title")
        val title: String? = null
    )
}