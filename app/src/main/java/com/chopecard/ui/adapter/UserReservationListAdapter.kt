package com.chopecard.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.R
import com.chopecard.domain.models.UserReservation

class UserReservationListAdapter(
    private val reservationList: List<UserReservation>,
    private val onClick: (UserReservation) -> Unit
) : RecyclerView.Adapter<UserReservationListAdapter.ReservationViewHolder>() {

    class ReservationViewHolder(view: View, val onClick: (UserReservation) -> Unit) : RecyclerView.ViewHolder(view) {
        private val storeName: TextView = view.findViewById(R.id.storeName)
        private val productName: TextView = view.findViewById(R.id.productName)
        private val productQuantity: TextView = view.findViewById(R.id.productQuantity)
        private var currentReservation: UserReservation? = null

        init {
            view.setOnClickListener {
                currentReservation?.let { onClick(it) }
            }
        }

        fun bind(reservation: UserReservation) {

            currentReservation = reservation
            storeName.text = reservation.store.name
            productName.text = reservation.productStore.product.name
            productQuantity.text = reservation.quantity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reservation_item, parent, false)
        return ReservationViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {

        holder.bind(reservationList[position])
    }

    override fun getItemCount() = reservationList.size
}
