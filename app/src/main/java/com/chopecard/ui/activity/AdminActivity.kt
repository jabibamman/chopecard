package com.chopecard.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.ComponentActivity
import com.chopecard.R
import com.chopecard.data.repository.AdminRepository
import com.chopecard.domain.models.Ticket
import com.chopecard.presentation.viewModel.TicketViewModel
import org.koin.android.ext.android.inject

class AdminActivity : ComponentActivity() {
    private val adminRepository: AdminRepository by inject()
    private val ticketViewModel = TicketViewModel(adminRepository)
    private lateinit var tickets: List<Ticket>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_layout)

        val listView = findViewById<ListView>(R.id.listViewTickets)
        tickets = ticketViewModel.getTickets().value.orEmpty()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tickets)
        listView.adapter = adapter
    }
}