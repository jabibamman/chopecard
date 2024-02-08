package com.chopecard.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.R
import com.chopecard.domain.models.Ticket
import com.chopecard.presentation.viewModel.TicketViewModel
import com.chopecard.ui.utils.showConfirmationAlert

class TicketListAdapter(
    private val ticketList: MutableList<Ticket>,
    private val viewModel: TicketViewModel,
    private val context: Context
) : RecyclerView.Adapter<TicketListAdapter.ViewHolder>() {
        class ViewHolder(view: View, viewModel: TicketViewModel, context: Context) : RecyclerView.ViewHolder(view) {
            private val subject: TextView = view.findViewById(R.id.ticketSubject)
            private val messages: TextView = view.findViewById(R.id.ticketMessages)
            private val deleteButton: ImageButton = view.findViewById(R.id.ticketDelete)
            private var currentTicket: Ticket? = null

            init {
                deleteButton.setOnClickListener {
                    currentTicket?.let { ticket ->
                        Log.d("TicketListAdapter", "Delete button clicked: $ticket")
                        showConfirmationAlert(
                            "Are you sure you want to delete this ticket?", context) {
                            viewModel.deleteTicket(ticket.ticketId)
                        }
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
            return ViewHolder(view, viewModel, context)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(ticketList[position])
        }

        override fun getItemCount() = ticketList.size

    fun updateList(newList: List<Ticket>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = ticketList.size

            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return ticketList[oldItemPosition].ticketId == newList[newItemPosition].ticketId
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return ticketList[oldItemPosition] == newList[newItemPosition]
            }
        })

        Log.d("TicketListAdapter", "Tickets: $newList")
        ticketList.clear()
        ticketList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}
