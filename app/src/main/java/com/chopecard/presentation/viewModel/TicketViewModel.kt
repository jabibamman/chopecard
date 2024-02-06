package com.chopecard.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.data.model.CreateTicketDTO
import com.chopecard.domain.models.Ticket
import com.chopecard.domain.models.TicketMessage
import com.chopecard.domain.usecases.AddTicketUseCase
import com.chopecard.domain.usecases.GetTicketsUseCase
import kotlinx.coroutines.launch

/** Represents the UI state. */
sealed class TicketDataState {
    object Loading : TicketDataState()
    data class Success(val tickets: List<Ticket>): TicketDataState()
    data class Error(val exception: Throwable): TicketDataState()
}

class TicketViewModel(
    private val getTicketsUseCase: GetTicketsUseCase,
    private val addTicketUseCase: AddTicketUseCase
): ViewModel() {

    val ticketsLiveData = MutableLiveData<TicketDataState>()
    val alertMessage = MutableLiveData<String>()

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

    fun addTicket(createTicketDTO: CreateTicketDTO) {
        Log.d("TicketViewModel", "Add ticket button clicked : $createTicketDTO")
        val ticketMessages = TicketMessage(createTicketDTO.message)
        val ticket = Ticket((0..9999999).random(), createTicketDTO.subject, listOf(ticketMessages))

        viewModelScope.launch {
            try {
                val result = addTicketUseCase.execute(ticket)
                if(!result) {
                    alertMessage.postValue("An error occured when adding ticket.")
                    return@launch
                }
                Log.d("TicketViewModel", "Successfully added ticket: $result")
                alertMessage.postValue("Ticket successfully addded.")
            }
            catch (e: Exception) {
                Log.e("SellerViewModel", "Error adding product", e)
                alertMessage.postValue("An error occured when adding ticket.")
            }
        }
    }
}
