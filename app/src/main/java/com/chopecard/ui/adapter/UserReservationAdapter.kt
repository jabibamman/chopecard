package com.chopecard.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.R
import com.chopecard.domain.models.UserReservation

class UserReservationAdapter(private val userReservationList: MutableList<UserReservation>, private val onClick: (UserReservation) -> Unit) :
    RecyclerView.Adapter<UserReservationAdapter.ViewHolder>() {

    class ViewHolder(view: View, val onClick: (UserReservation) -> Unit) : RecyclerView.ViewHolder(view) {
        private val storeName: TextView = view.findViewById(R.id.tvStoreName)
        private val productName: TextView = view.findViewById(R.id.tvProductName)
        private val productPrice: TextView = view.findViewById(R.id.tvProductPrice)
        private val quantity: TextView = view.findViewById(R.id.tvQuantity)
        private val id: TextView = view.findViewById(R.id.tvId)

        private var currentUserReservation: UserReservation? = null

        init {
            view.setOnClickListener {
                currentUserReservation?.let { onClick(it) }
            }
        }

        fun bind(userReservation: UserReservation) {
            currentUserReservation = userReservation
            storeName.text = userReservation.store.name
            productName.text = userReservation.productStore.product.name
            productPrice.text = userReservation.productStore.price.toString()
            quantity.text = userReservation.quantity.toString()
            id.text = userReservation.id.toString()
        }
    }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reservation_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userReservationList[position])
    }

    override fun getItemCount() = userReservationList.size

    fun updateList(newList: List<UserReservation>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = userReservationList.size

            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return userReservationList[oldItemPosition].id == newList[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return userReservationList[oldItemPosition] == newList[newItemPosition]
            }
        })

        userReservationList.clear()
        userReservationList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}
