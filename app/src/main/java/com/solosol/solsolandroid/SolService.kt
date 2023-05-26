package com.solosol.solsolandroid

import com.solosol.solsolandroid.response.BannerResponse
import com.solosol.solsolandroid.response.MyAccountListResponse
import com.solosol.solsolandroid.response.MyAccountResponse
import com.solosol.solsolandroid.response.RecentTransferResponse
import com.solosol.solsolandroid.response.RequestTransferDto
import com.solosol.solsolandroid.response.ResponseTransferDto
import com.solosol.solsolandroid.response.TransferDeleteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SolService {

    @GET("/api/ads")
    suspend fun getBannerInfo(): Response<BannerResponse>

    @GET("/api/accounts/{accountId}")
    suspend fun getAccountInfo(
        @Path("accountId") accountId: String,
        @Query("memberId") memberId: Int
    ): Response<MyAccountResponse>

    @GET("/api/accounts/?memberId=1")
    suspend fun getAccountListInfo(): Response<MyAccountListResponse>

    @POST("/api/transfers?memberId=1")
    suspend fun postTransfer(
        @Body request: RequestTransferDto,
    ): Response<ResponseTransferDto>

    @GET("/api/transfers")
    suspend fun getRecentTransferAccounts(
        @Query("memberId") memberId: Int,
    ): Response<RecentTransferResponse>

    @DELETE("/api/transfers/{transferId}?memberId=1")
    suspend fun deleteTransfer(
        @Path("transferId") transferId: Int,
    ): Response<TransferDeleteResponse>
}