package com.chopecard.ui.activity

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.R
import com.chopecard.domain.models.Ticket

class TicketListAdapter(private val ticketList: MutableList<Ticket>, private val onClick: (Ticket) -> Unit) :
    RecyclerView.Adapter<TicketListAdapter.ViewHolder>() {
        class ViewHolder(view: View, val onClick: (Ticket) -> Unit) : RecyclerView.ViewHolder(view) {
            private val subject: TextView = view.findViewById(R.id.ticketSubject)
            private val messages: TextView = view.findViewById(R.id.ticketMessages)
            private var currentTicket: Ticket? = null

            init {
                view.setOnClickListener {
                    currentTicket?.let { onClick(it) }
                }
            }

            fun bind(ticket: Ticket) {
                currentTicket = ticket
                subject.text = ticket.subject
                messages.text = ticket.messages[0].content
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.ticket_item, parent, false)
            return ViewHolder(view, onClick)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(ticketList[position])
        }

        override fun getItemCount() = ticketList.size

        fun updateList(newList: List<Ticket>) {
            Log.d("TicketListAdapter", "Tickets: $newList")
            ticketList.clear()
            ticketList.addAll(newList)
            notifyDataSetChanged()
        }
}
