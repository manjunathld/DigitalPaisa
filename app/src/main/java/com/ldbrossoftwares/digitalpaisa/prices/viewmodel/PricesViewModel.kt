package com.ldbrossoftwares.digitalpaisa.prices.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ldbrossoftwares.digitalpaisa.adapter.PricesAdapter
import com.ldbrossoftwares.digitalpaisa.prices.model.NetworkAPI
import com.ldbrossoftwares.digitalpaisa.repository.PricesRepository

class PricesViewModel : ViewModel() {

    private val mPricesRepository: PricesRepository = PricesRepository()

    fun callGetAssetsAPI(v2: String,assets: String): MutableLiveData<String> {
        return mPricesRepository.callGetAssetsAPI(v2, assets)
    }

    fun getNetworkServiceInstance(): NetworkAPI {
        return mPricesRepository.getNetworkServiceInstance()
    }

    fun getAssetsResponseMutableLiveData(): MutableLiveData<String> {
        return mPricesRepository.getAssetsResponseMutableLiveData()
    }

    fun getPricesAdapterInstance(): PricesAdapter {
        return mPricesRepository.getPricesAdapterInstance()
    }

}