package com.ldbrossoftwares.digitalpaisa.prices.view


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ldbrossoftwares.digitalpaisa.Constants
import com.ldbrossoftwares.digitalpaisa.R
import com.ldbrossoftwares.digitalpaisa.adapter.PricesAdapter
import com.ldbrossoftwares.digitalpaisa.prices.viewmodel.PricesViewModel
import com.ldbrossoftwares.digitalpaisa.services.PricesServices

/**
 * A simple [Fragment] subclass.
 */
class PriceFragment : Fragment() {

    private lateinit var mMenuIconImageView: ImageView
    private lateinit var mPricesViewModel: PricesViewModel
    private lateinit var mPricesAdapter: PricesAdapter
    private lateinit var mPricesListRecyclerView: RecyclerView
    private lateinit var mVolumeUSD24Hr: TextView
    private lateinit var mBottomNavigationOptions: BottomNavigationView
    private lateinit var mGlobalMarketCap: ConstraintLayout
    private lateinit var mSearchCoinsEditText: EditText
    private lateinit var mSearchIconImageVIew: ImageView

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPricesViewModel = PricesServices.getPricesViewModelInstance(this)
        mPricesAdapter = mPricesViewModel.getPricesAdapterInstance()
        mPricesViewModel.callGetAssetsAPI(Constants.V2, Constants.ASSETS)
        Log.d("Assets", "API Called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.price_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            mPricesListRecyclerView = findViewById(R.id.rv_assets_pricesFragment)
            mVolumeUSD24Hr = findViewById(R.id.tv_volume_usd24hr_pricesFragment)
            mMenuIconImageView = findViewById(R.id.iv_menu_icon_commonActionBar)
            mGlobalMarketCap = findViewById(R.id.cl_global_market_cap_priceFragment)
            mBottomNavigationOptions = findViewById(R.id.bnv_options_pricesFragment)
            mSearchCoinsEditText = findViewById(R.id.et_search_coins_pricesFragment)
            mSearchIconImageVIew = findViewById(R.id.iv_search_icon_pricesFragment)
        }

        mMenuIconImageView.setOnClickListener(mOnClickListener)
        mGlobalMarketCap.setOnClickListener(mOnClickListener)
        mSearchIconImageVIew.setOnClickListener(mOnClickListener)
        mBottomNavigationOptions.setOnNavigationItemSelectedListener(mOnNavigationItemSelectListener)
        //mPricesListRecyclerView.addOnItemTouchListener(mOnItemTouchListener)
        mSearchCoinsEditText.addTextChangedListener(mSearchCoinsTextWatcher)

        mVolumeUSD24Hr.text = getString(R.string.dummy_text)

        mPricesListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = mPricesAdapter
            setHasFixedSize(true)
        }

    }

    private val mOnClickListener: View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.iv_menu_icon_commonActionBar -> {
                Toast.makeText(context, R.string.coming_soon, Toast.LENGTH_SHORT).show()
            }
            R.id.cl_global_market_cap_priceFragment-> {
                Toast.makeText(context, R.string.coming_soon, Toast.LENGTH_SHORT).show()
            }
            R.id.iv_search_icon_pricesFragment -> {
                if (mSearchCoinsEditText.text.toString().isNotEmpty()) {
                    mSearchCoinsEditText.setText(Constants.EMPTY_STRING)
                    mSearchIconImageVIew.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_search_black))
                } else if (mSearchCoinsEditText.text.toString().isEmpty()) {
                    mSearchCoinsEditText.requestFocus()
                }
            }
            else -> {}
        }
    }

    private val mOnNavigationItemSelectListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.miv_prices_bottomNavigationItems -> {
                Toast.makeText(context, R.string.coming_soon, Toast.LENGTH_SHORT).show()
            }
            R.id.miv_favorites_bottomNavigationItems -> {
                Toast.makeText(context, R.string.coming_soon, Toast.LENGTH_SHORT).show()
            }
            R.id.miv_portfolio_bottomNavigationItems -> {
                Toast.makeText(context, R.string.coming_soon, Toast.LENGTH_SHORT).show()
            }
            R.id.miv_news_bottomNavigationItems -> {
                Toast.makeText(context, R.string.coming_soon, Toast.LENGTH_SHORT).show()
            }
            R.id.miv_invest_bottomNavigationItems -> {
                Toast.makeText(context, R.string.coming_soon, Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
        true
    }

    private val mSearchCoinsTextWatcher: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(editableText: Editable?) {
            val searchCoins: String = editableText.toString()
            if (searchCoins.isNotEmpty()) {
                mSearchIconImageVIew.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_close_black))
            } else if (searchCoins.isEmpty()) {
                mSearchIconImageVIew.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_search_black))
            }
            mPricesAdapter.filter.filter(searchCoins)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

}
