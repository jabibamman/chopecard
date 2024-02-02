package com.chopecard.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.R
import com.chopecard.domain.models.Store

class ShopListAdapter(private val shopList: MutableList<Store>, private val onClick: (Store) -> Unit) :
    RecyclerView.Adapter<ShopListAdapter.ViewHolder>() {

    class ViewHolder(view: View, val onClick: (Store) -> Unit) : RecyclerView.ViewHolder(view) {
        private val shopName: TextView = view.findViewById(R.id.tvShopName)
        private var currentStore: Store? = null

        init {
            view.setOnClickListener {
                currentStore?.let { onClick(it) }
            }
        }

        fun bind(store: Store) {
            currentStore = store
            shopName.text = store.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shop_list_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shopList[position])
    }

    override fun getItemCount() = shopList.size

    fun updateList(newList: List<Store>) {
        shopList.clear()
        shopList.addAll(newList)
        notifyDataSetChanged()
    }
}
