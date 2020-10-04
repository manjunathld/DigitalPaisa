package com.ldbrossoftwares.digitalpaisa.prices.model

import com.ldbrossoftwares.digitalpaisa.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    fun createNetworkService(): NetworkAPI {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val networkAPI: NetworkAPI = retrofit.create(NetworkAPI::class.java)

        return networkAPI

    }

}