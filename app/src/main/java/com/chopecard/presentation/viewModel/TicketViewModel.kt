package com.chopecard.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.domain.models.Ticket
import com.chopecard.domain.usecases.GetTicketsUseCase
import kotlinx.coroutines.launch

class TicketViewModel(private val getTicketsUseCase: GetTicketsUseCase): ViewModel() {
    val ticketsLiveData = MutableLiveData<List<Ticket>>()

    fun getTickets() {
        viewModelScope.launch {
            try {
                val tickets = getTicketsUseCase.execute()
                ticketsLiveData.postValue(tickets)
            } catch (e: Exception) {
                Log.e("TicketViewModel", "Exception when calling use case: ", e)
                ticketsLiveData.postValue(emptyList())
            }
        }
    }
}