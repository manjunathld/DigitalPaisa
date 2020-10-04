package com.ldbrossoftwares.digitalpaisa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ldbrossoftwares.digitalpaisa.Constants
import com.ldbrossoftwares.digitalpaisa.R
import com.ldbrossoftwares.digitalpaisa.prices.model.AssetsData

class PricesAdapter : RecyclerView.Adapter<PricesAdapter.PricesViewHolder>(), Filterable{

    private lateinit var mListOfAssets: ArrayList<AssetsData>
    private lateinit var mListOfAssetsOriginal: ArrayList<AssetsData>

    fun setAssetsDataToPricesAdapter(listOfAssets: ArrayList<AssetsData>) {
        mListOfAssets = listOfAssets
        mListOfAssetsOriginal = ArrayList<AssetsData>(mListOfAssets)
        notifyDataSetChanged()
    }

    class PricesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val serialNumberTextView: TextView = itemView.findViewById(R.id.tv_sl_no_pricesFragment)
        private val symbolTextView: TextView = itemView.findViewById(R.id.tv_symbol_pricesFragment)
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_name_recyclerViewItemPricesFragment)
        private val priceUSDTextView: TextView = itemView.findViewById(R.id.tv_price_usd_pricesFragment)
        private val changePercent24HoursTextView: TextView = itemView.findViewById(R.id.tv_change_percent24hr_pricesFragment)

        fun bindDataToItems(slNo: Int, assetsData: AssetsData) {
            val dollar: String = "${Constants.DOLLAR}${Constants.SIX_DECIMAL_POINT.format(assetsData.priceUSD)}"
            val percentage: String = "${Constants.TWO_DECIMAL_POINT.format(assetsData.changePercent24Hours)}${Constants.PERCENT}"
            symbolTextView.text = assetsData.symbol
            serialNumberTextView.text = (slNo + Constants.ONE).toString()
            nameTextView.text = assetsData.name
            priceUSDTextView.text = dollar
            changePercent24HoursTextView.text = percentage

            if (assetsData.changePercent24Hours > 0.0) {
                changePercent24HoursTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorGreen))
            } else {
                changePercent24HoursTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorRed))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PricesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.price_fragment_recycler_view_item, parent, false)

        return PricesViewHolder(view)
    }

    override fun onBindViewHolder(pricesViewholder: PricesViewHolder, position: Int) {
        val assetsData: AssetsData = mListOfAssets[position]
        pricesViewholder.bindDataToItems(position, assetsData)
    }

    override fun getItemCount(): Int {
        if (::mListOfAssets.isInitialized) {
            return mListOfAssets.size
        } else {
            return 0;
        }
    }

    override fun getFilter(): Filter {
        return mFilterListOfAssets
    }

    private val mFilterListOfAssets: Filter = object : Filter() {
        //Run on Background thread
        override fun performFiltering(charSequence: CharSequence?): FilterResults {
            val listOfAssetsFilter: ArrayList<AssetsData> = ArrayList<AssetsData>()

            if (charSequence.toString().isEmpty()) {
                listOfAssetsFilter.addAll(mListOfAssetsOriginal)
            } else {
                for (searchCoins in mListOfAssetsOriginal) {
                    if (searchCoins.name.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        listOfAssetsFilter.add(searchCoins)
                    }
                }
            }

            val filterResults: FilterResults = FilterResults()
            filterResults.values = listOfAssetsFilter

            return  filterResults
        }

        //Run on UI thread
        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
            mListOfAssets.clear()
            mListOfAssets.addAll(filterResults?.values as Collection<AssetsData>)
            notifyDataSetChanged()
        }
    }

}