package com.chopecard.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.R
import com.chopecard.presentation.viewModel.TicketDataState
import com.chopecard.presentation.viewModel.TicketViewModel
import com.chopecard.ui.adapter.TicketListAdapter
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
        val adapter = TicketListAdapter(mutableListOf()) { }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun observeTickets() {
        ticketViewModel.ticketsLiveData.observe(this) { ticketDataState ->
            when(ticketDataState) {
                is TicketDataState.Loading -> {
                    findViewById<ProgressBar>(R.id.adminProgressBar).visibility = View.VISIBLE
                }
                is TicketDataState.Success -> {
                    findViewById<ProgressBar>(R.id.adminProgressBar).visibility = View.GONE
                    val adapter = findViewById<RecyclerView>(R.id.rvTickets).adapter as? TicketListAdapter
                    adapter?.updateList(ticketDataState.tickets)
                }
                is TicketDataState.Error -> {
                    findViewById<ProgressBar>(R.id.adminProgressBar).visibility = View.GONE
                    Log.e("AdminActivity", "Error getting tickets", ticketDataState.exception)
                }
            }
        }
    }
}
