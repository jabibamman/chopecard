package com.chopecard.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chopecard.R
import com.chopecard.data.model.CreateTicketDTO
import com.chopecard.presentation.viewModel.TicketDataState
import com.chopecard.presentation.viewModel.TicketViewModel
import com.chopecard.ui.adapter.TicketListAdapter
import com.chopecard.ui.utils.showAlert
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminActivity : BaseActivity() {
    private val ticketViewModel: TicketViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_layout)
        setupFooter()
        setupListener()
        setupRecyclerView()
        observeTickets()
        ticketViewModel.getTickets()
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvTickets)
        val adapter = TicketListAdapter(mutableListOf(), ticketViewModel)
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

    private fun setupListener() {
        findViewById<Button>(R.id.addTicket)?.setOnClickListener {
            Log.d("AdminActivity", "Add ticket button clicked")
            onAddTicket()
        }
    }

    private fun onAddTicket() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_ticket, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Add New Ticket")
            .setPositiveButton("Add", null)
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .create()

        dialog.setOnShowListener {
            val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener {
                val subjectText = dialogView.findViewById<EditText>(R.id.etSubject).text.toString()
                val descriptionText = dialogView.findViewById<EditText>(R.id.etDescription).text.toString()

                if(subjectText.isEmpty()) {
                    showAlert("Please fill the subject of the ticket", this)
                } else {
                    ticketViewModel.addTicket(CreateTicketDTO(subjectText, descriptionText))
                    dialog.dismiss()
                }
            }
        }

        dialog.show()
    }
}
