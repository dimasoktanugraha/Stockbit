package com.stockbit.stockbit.ui.watch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.stockbit.R
import com.stockbit.stockbit.core.data.source.remote.response.CryptoData
import com.stockbit.stockbit.databinding.ItemWatchBinding
import java.util.ArrayList

class WatchAdapter: RecyclerView.Adapter<WatchAdapter.WatchViewHolder>() {

    private var listData = ArrayList<CryptoData>()

    fun setData(newListData: List<CryptoData>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    fun addData(addData: List<CryptoData>) {
        listData.addAll(addData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchViewHolder =
        WatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_watch, parent, false))


    override fun onBindViewHolder(holder: WatchViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size


    inner class WatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemWatchBinding.bind(itemView)
        fun bind(data: CryptoData) {
            binding.itemWatchId.text = data.CoinInfo.Name
            binding.itemWatchCompany.text = data.CoinInfo.FullName
            binding.itemWatchPrice.text = data.DISPLAY.USD.PRICE

            val change = data.DISPLAY.USD.CHANGE24HOUR
            binding.itemWatchPercentage.text = change
            if (change.contains("-")){
                binding.itemWatchPercentage.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
            }else{
                binding.itemWatchPercentage.setTextColor(ContextCompat.getColor(itemView.context, R.color.green))
            }

        }
    }


}