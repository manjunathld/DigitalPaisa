package com.ldbrossoftwares.digitalpaisa.prices.model

import com.ldbrossoftwares.digitalpaisa.Constants
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkAPI {

    @GET("${Constants.V2}${Constants.ASSETS}")
    //@GET
    fun getV2Assets(@Query("v2") v2: String, @Query("assets") assets: String): Call<ResponseBody>

}