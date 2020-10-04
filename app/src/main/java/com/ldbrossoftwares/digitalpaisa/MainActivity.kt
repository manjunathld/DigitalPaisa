package com.ldbrossoftwares.digitalpaisa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ldbrossoftwares.digitalpaisa.adapter.PricesAdapter
import com.ldbrossoftwares.digitalpaisa.prices.model.AssetsData
import com.ldbrossoftwares.digitalpaisa.prices.viewmodel.PricesViewModel
import com.ldbrossoftwares.digitalpaisa.services.PricesServices
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var mPricesViewModel: PricesViewModel
    private lateinit var mAssetsResponseMutableLiveData: MutableLiveData<String>
    private lateinit var mAssetsJSONArray: JSONArray
    private lateinit var mAssetsJSONObject: JSONObject
    private lateinit var mListOfAssets: ArrayList<AssetsData>
    private lateinit var mPricesAdapter: PricesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPricesViewModel = PricesServices.getPricesViewModelInstance(this)
        //mPricesViewModel.callGetAssetsAPI(Constants.V2, Constants.ASSETS)
        mAssetsResponseMutableLiveData = mPricesViewModel.getAssetsResponseMutableLiveData()
        mPricesAdapter = mPricesViewModel.getPricesAdapterInstance()
        mListOfAssets = arrayListOf<AssetsData>()

        mAssetsResponseMutableLiveData.observe(this, mAssetsResponseMutableLiveDataObserve)

    }

    private val mAssetsResponseMutableLiveDataObserve: Observer<String> = object : Observer<String> {
        override fun onChanged(assetsResponse: String?) {
            Log.d("Assets, OnChange", assetsResponse!!)
            if (assetsResponse.isNotEmpty() && assetsResponse[0] == '{') {
                try {
                    var symbols: String
                    var name: String
                    var priceUSD: Double
                    var changePercent24Hours: Double
                    mAssetsJSONObject = JSONObject(assetsResponse)
                    mAssetsJSONArray = JSONArray(mAssetsJSONObject.getString("data"))

                    mListOfAssets.clear()
                    for (index: Int in 0 until mAssetsJSONArray.length()) {
                        val assetsDataJsonObject: JSONObject = JSONObject((mAssetsJSONArray[index]).toString())
                        with (assetsDataJsonObject) {
                            symbols = getString("symbol")
                            name = getString("name")
                            priceUSD = getDouble("priceUsd")
                            changePercent24Hours = getDouble("changePercent24Hr")
                        }
                        mListOfAssets.add(AssetsData(symbols, name, priceUSD, changePercent24Hours))
                    }

                    mPricesAdapter.setAssetsDataToPricesAdapter(mListOfAssets)

                } catch (jsonException: JSONException) {
                    jsonException.printStackTrace()
                }
            }
        }

    }

}
