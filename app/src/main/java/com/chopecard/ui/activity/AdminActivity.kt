package com.chopecard.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.chopecard.R
import com.chopecard.presentation.viewModel.TicketViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminActivity : BaseActivity() {
    private val ticketViewModel: TicketViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_layout)
        setupFooter()
        observeTickets()
        ticketViewModel.getTickets()
    }

    private fun observeTickets() {
        ticketViewModel.ticketsLiveData.observe(this) { tickets ->
            val listView = findViewById<ListView>(R.id.listViewTickets)
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tickets)
            listView.adapter = adapter
        }
    }
}
