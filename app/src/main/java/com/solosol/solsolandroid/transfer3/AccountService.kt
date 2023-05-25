package com.solosol.solsolandroid.transfer3

import retrofit2.Call
import retrofit2.http.GET

interface AccountService {
    @GET("/api/accounts/?memberId=1")
    fun getAccount(): Call<ResponseAccountDto>
}