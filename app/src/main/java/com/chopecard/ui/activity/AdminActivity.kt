package com.chopecard.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.R
import com.chopecard.presentation.viewModel.TicketViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminActivity : BaseActivity() {
    private val ticketViewModel: TicketViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_layout)
        setupFooter()
        setupRecyclerView()
        observeTickets()
        ticketViewModel.getTickets()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvTickets)
        val adapter = TicketListAdapter(mutableListOf()) { ticket ->
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun observeTickets() {
        ticketViewModel.ticketsLiveData.observe(this) { tickets ->
            val adapter = findViewById<RecyclerView>(R.id.rvTickets).adapter as? TicketListAdapter
            adapter?.updateList(tickets)
        }
    }
}
