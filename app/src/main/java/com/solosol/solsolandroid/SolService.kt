package com.solosol.solsolandroid

import com.solosol.solsolandroid.response.AccountInfoResponse
import com.solosol.solsolandroid.response.BannerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SolService {

    @GET("/api/ads")
    suspend fun getBannerInfo(): Response<BannerResponse>

    @GET("/api/accounts/{accountId}")
    suspend fun getAccountInfo(
        @Path("accountId") accountId: String,
        @Query("memberId") memberId: Int
    ): Response<AccountInfoResponse>
}