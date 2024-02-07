package com.chopecard.domain.usecases

import com.chopecard.data.repository.AdminRepository
import com.chopecard.domain.models.Ticket

class GetTicketsUseCase(private val repository: AdminRepository) {
    suspend fun execute(): List<Ticket> {
        try {
            return repository.getTickets()
        } catch (e: Exception) {
            throw e
        }
    }
}
