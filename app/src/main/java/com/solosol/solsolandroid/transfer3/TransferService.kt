package com.solosol.solsolandroid.transfer3

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TransferService {
    @POST("api/transfers?memberId=1")
    fun postTransfer(
        @Body request: RequestTransferDto,
    ): Call<ResponseTransferDto>
}