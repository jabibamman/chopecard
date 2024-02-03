package com.chopecard.data.repository

import com.chopecard.data.model.ProductStoreDTO
import com.chopecard.domain.models.Product
import com.chopecard.domain.models.Ticket
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AdminRepository {
    suspend fun createTicket(ticket: Ticket): String

    suspend fun createProduct(product: Product)

    suspend fun getTickets(): List<Ticket>

    suspend fun getTicketById(ticketId: Int): Ticket

    suspend fun deleteTicketById(ticketId: Int): String

    suspend fun deleteProductById(productId: Int): String
}