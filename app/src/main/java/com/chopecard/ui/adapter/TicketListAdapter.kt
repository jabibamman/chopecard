package com.chopecard.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.R
import com.chopecard.domain.models.Ticket

class TicketListAdapter(private val ticketList: MutableList<Ticket>) :
    RecyclerView.Adapter<TicketListAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val subject: TextView = view.findViewById(R.id.ticketSubject)
            private val messages: TextView = view.findViewById(R.id.ticketMessages)
            private val deleteButton: ImageButton = view.findViewById(R.id.ticketDelete)
            private var currentTicket: Ticket? = null

            init {
                deleteButton.setOnClickListener {
                    currentTicket?.let { ticket ->
                        Log.d("TicketListAdapter", "Delete button clicked: $ticket")
                    }
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
            return ViewHolder(view)
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
