package com.chopecard.domain.usecases

import com.chopecard.data.model.DeleteTicketDTO
import com.chopecard.data.repository.AdminRepository

class DeleteTicketUseCase(private val repository: AdminRepository) {
    suspend fun execute(deleteTicketDTO: DeleteTicketDTO) : Boolean {
        return repository.deleteTicketById(deleteTicketDTO)
    }
}