package com.ldbrossoftwares.digitalpaisa.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ldbrossoftwares.digitalpaisa.adapter.PricesAdapter
import com.ldbrossoftwares.digitalpaisa.prices.model.NetworkAPI
import com.ldbrossoftwares.digitalpaisa.services.PricesServices
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PricesRepository {

    private lateinit var mNetworkAPI: NetworkAPI
    private lateinit var mAssetsResponseMutableLiveData: MutableLiveData<String>

    fun callGetAssetsAPI(v2: String, assets: String): MutableLiveData<String> {
        mNetworkAPI = getNetworkServiceInstance()
        mAssetsResponseMutableLiveData = getAssetsResponseMutableLiveData()

        mNetworkAPI.getV2Assets(v2, assets).enqueue(mGetAssetsResponseCallback)

        return mAssetsResponseMutableLiveData

    }

    private val mGetAssetsResponseCallback: Callback<ResponseBody> = object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            mAssetsResponseMutableLiveData.value = t.localizedMessage
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                mAssetsResponseMutableLiveData.value = response.body()?.string()
            } else {
                mAssetsResponseMutableLiveData.value = response.errorBody()?.string()
            }
        }
    }

    fun getNetworkServiceInstance(): NetworkAPI {
       return PricesServices.getNetworkServiceInstance()
    }

    fun getAssetsResponseMutableLiveData(): MutableLiveData<String> {
        return PricesServices.getAssetsResponseMutableLiveData()
    }

    fun getPricesAdapterInstance(): PricesAdapter {
        return PricesServices.getPricesAdapterInstance()
    }

}