package com.ldbrossoftwares.digitalpaisa.services

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.ldbrossoftwares.digitalpaisa.adapter.PricesAdapter
import com.ldbrossoftwares.digitalpaisa.prices.model.NetworkAPI
import com.ldbrossoftwares.digitalpaisa.prices.model.NetworkService
import com.ldbrossoftwares.digitalpaisa.prices.viewmodel.PricesViewModel

object PricesServices {

    private lateinit var mNetworkAPI: NetworkAPI
    private lateinit var mAssetsResponseMutableLiveData: MutableLiveData<String>
    private lateinit var mPricesViewModel: PricesViewModel
    private lateinit var mPricesAdapter: PricesAdapter

    fun getNetworkServiceInstance(): NetworkAPI {
        if (!(::mNetworkAPI.isInitialized)) {
            mNetworkAPI = NetworkService.createNetworkService()
        }

        return mNetworkAPI
    }

    fun getAssetsResponseMutableLiveData(): MutableLiveData<String> {
        if (!(::mAssetsResponseMutableLiveData.isInitialized)) {
            mAssetsResponseMutableLiveData = MutableLiveData<String>()
        }

        return mAssetsResponseMutableLiveData
    }

    fun getPricesViewModelInstance(viewModelStoreOwner: ViewModelStoreOwner): PricesViewModel {
        if (!(::mPricesViewModel.isInitialized)) {
            mPricesViewModel = ViewModelProvider(viewModelStoreOwner).get(
                PricesViewModel::class.java
            )
        }

        return mPricesViewModel
    }

    fun getPricesAdapterInstance(): PricesAdapter {
        if (!(::mPricesAdapter.isInitialized)) {
            mPricesAdapter = PricesAdapter()
        }

        return mPricesAdapter
    }

}