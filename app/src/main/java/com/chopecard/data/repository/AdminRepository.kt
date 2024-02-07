package com.chopecard.data.repository

import com.chopecard.domain.models.Product
import com.chopecard.domain.models.Ticket

interface AdminRepository {
    /** Creates a new ticket */
    suspend fun createTicket(ticket: Ticket): Boolean

    /** Creates a new product */
    suspend fun createProduct(product: Product)

    /** Retrieves a list of all tickets that has been created */
    suspend fun getTickets(): List<Ticket>

    /** Retrieves details of a specific ticket by ticket ID */
    suspend fun getTicketById(ticketId: Int): Ticket

    /** Deletes a ticket from the system */
    suspend fun deleteTicketById(ticketId: Int): String

    /** Deletes a product from the system */
    suspend fun deleteProductById(productId: Int): String
}