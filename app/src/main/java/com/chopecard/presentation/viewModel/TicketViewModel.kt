package com.chopecard.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chopecard.data.repository.AdminRepository
import com.chopecard.domain.models.Ticket
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TicketViewModel(private val adminRepository: AdminRepository): ViewModel() {
    private val tickets: MutableLiveData<List<Ticket>> by lazy {
        MutableLiveData<List<Ticket>>().also {
            loadTickets()
        }
    }

    fun getTickets(): LiveData<List<Ticket>> {
        return tickets
    }

    private fun loadTickets(): List<Ticket> {
        var ticketsFromRepo = listOf<Ticket>()
        runBlocking {
            launch {
                ticketsFromRepo = adminRepository.getTickets()
            }
        }
        return ticketsFromRepo
    }
}