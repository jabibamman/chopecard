package com.chopecard.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.domain.models.Ticket
import com.chopecard.domain.usecases.GetTicketsUseCase
import kotlinx.coroutines.launch

/** Represents the UI state. */
sealed class TicketDataState {
    object Loading : TicketDataState()
    data class Success(val tickets: List<Ticket>): TicketDataState()
    data class Error(val exception: Throwable): TicketDataState()
}

class TicketViewModel(
    private val getTicketsUseCase: GetTicketsUseCase
): ViewModel() {

    val ticketsLiveData = MutableLiveData<TicketDataState>()

    fun getTickets() {
        ticketsLiveData.postValue(TicketDataState.Loading)
        viewModelScope.launch {
            try {
                val tickets = getTicketsUseCase.execute()
                ticketsLiveData.postValue(TicketDataState.Success(tickets))
                Log.d("TicketViewModel", "Tickets: $tickets")
            } catch (e: Exception) {
                Log.e("TicketViewModel", "Exception when calling use case: ", e)
                ticketsLiveData.postValue(TicketDataState.Error(e))
            }
        }
    }
}