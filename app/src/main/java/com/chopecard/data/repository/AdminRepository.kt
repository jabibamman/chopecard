package com.chopecard.data.repository

import com.chopecard.domain.models.Product
import com.chopecard.domain.models.Ticket

interface AdminRepository {
    suspend fun createTicket(ticket: Ticket): String

    suspend fun createProduct(product: Product): String

    suspend fun getTickets(): List<Ticket>

    suspend fun getTicketById(ticketId: Int): Ticket

    suspend fun deleteTicketById(ticketId: Int): String

    suspend fun deleteProductById(productId: Int): String
}