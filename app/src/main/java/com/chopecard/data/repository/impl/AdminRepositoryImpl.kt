package com.chopecard.data.repository.impl

import com.chopecard.data.network.AdminApiService
import com.chopecard.data.repository.AdminRepository
import com.chopecard.domain.models.Product
import com.chopecard.domain.models.Ticket
import com.chopecard.domain.models.TicketMessage
import retrofit2.Call

class AdminRepositoryImpl(private val adminApiService: AdminApiService) : AdminRepository {
    override suspend fun createTicket(ticket: Ticket): String{
        val ticketDTO = adminApiService.createTicket(ticket)
        return try {
            val response = ticketDTO.execute()
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun createProduct(product: Product): String{
        val productDTO = adminApiService.createProduct(product)
        return try {
            val response = productDTO.execute()
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun getTickets(): List<Ticket>{
        val ticketsDTOs: Call<List<Ticket>> = adminApiService.getTickets()
        return try {
            val response = ticketsDTOs.execute()
            response.body() ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getTicketById(ticketId: Int): Ticket{
        val ticketDTO = adminApiService.getTicketById(ticketId)
        return try {
            val response = ticketDTO.execute()
            response.body() ?: Ticket(0, "", List<TicketMessage>(0) { TicketMessage("") })
        } catch (e: Exception) {
            Ticket(0, "", List<TicketMessage>(0) {TicketMessage("")})
        }
    }

    override suspend fun deleteTicketById(ticketId: Int): String{
        val ticketDTO = adminApiService.deleteTicketById(ticketId)
        return try {
            val response = ticketDTO.execute()
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun deleteProductById(productId: Int): String{
        val productDTO = adminApiService.deleteProductById(productId)
        return try {
            val response = productDTO.execute()
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }
}