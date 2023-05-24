package com.solosol.solsolandroid

import com.solosol.solsolandroid.response.BannerResponse
import retrofit2.Response
import retrofit2.http.GET

interface SolService {

    @GET("/api/ads")
    suspend fun getBannerInfo():Response<BannerResponse>
}