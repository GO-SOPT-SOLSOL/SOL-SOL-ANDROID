package com.solosol.solsolandroid.transfer3

import com.solosol.solsolandroid.response.RecentTransferResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TransferService {
    @POST("/api/transfers?memberId=1")
    suspend fun postTransfer(
        @Body request: RequestTransferDto,
    ): Response<ResponseTransferDto>

    @GET("/api/transfers")
    suspend fun getRecentTransferAccounts(
        @Query("memberId") memberId: Int,
    ): Response<RecentTransferResponse>
}