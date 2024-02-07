package com.chopecard.domain.usecases

import com.chopecard.data.repository.AdminRepository
import com.chopecard.domain.models.Ticket

class AddTicketUseCase(private val repository: AdminRepository) {
    suspend fun execute(ticket: Ticket) : Boolean {
        return repository.createTicket(ticket)
    }
}
