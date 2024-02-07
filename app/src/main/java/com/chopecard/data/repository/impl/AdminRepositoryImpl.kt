package com.chopecard.data.repository.impl

import android.util.Log
import com.chopecard.data.model.DeleteTicketDTO
import com.chopecard.data.network.AdminApiService
import com.chopecard.data.repository.AdminRepository
import com.chopecard.domain.models.Product
import com.chopecard.domain.models.Ticket
import com.chopecard.domain.models.TicketMessage

class AdminRepositoryImpl(private val adminApiService: AdminApiService) : AdminRepository {
    override suspend fun createTicket(ticket: Ticket): Boolean {
        return try {
            val response = adminApiService.createTicket(ticket)
            if(response.isSuccessful) {
                Log.d("AdminRepositoryImpl", "Ticket created: ${response.body()}")
                true
            } else {
                Log.e("AdminRepositoryImpl", "Error creating ticket: HTTP ${response.code()} ${response.message()}")
                false
            }
        } catch (e: Exception) {
            Log.e("AdminRepositoryImpl", "Error creating ticket: $e")
            false
        }
    }

    override suspend fun createProduct(product: Product) {
        try {
            val response = adminApiService.createProduct(product)
            if(response.isSuccessful) {
                Log.d("AdminRepositoryImpl", "Product created: ${response.body()}")
                response.body() ?: String()
            } else {
                Log.e("AdminRepositoryImpl", "Error creating product: HTTP ${response.code()} ${response.message()}")
                String()
            }
            response.body() ?: String()
        } catch (e: Exception) {
            Log.e("AdminRepositoryImpl", "Exception when calling API", e)
        }
    }

    override suspend fun getTickets(): List<Ticket> {
        return try {
            val response = adminApiService.getTickets()
            if(response.isSuccessful) {
                Log.d("AdminRepositoryImpl", "Tickets: ${response.body().toString()}")
                response.body() ?: emptyList()
            }
            else {
                Log.e("AdminRepositoryImpl", "Error getting tickets: HTTP ${response.code()} ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("AdminRepositoryImpl", "Exception when calling API", e)
            emptyList()
        }
    }

    override suspend fun getTicketById(ticketId: Int): Ticket {
        return try {
            val response = adminApiService.getTicketById(ticketId)
            if(response.isSuccessful) {
                Log.d("AdminRepositoryImpl", "Ticket: ${response.body().toString()}")
                response.body() ?: Ticket(0, "", List(0) { TicketMessage("") })
            }
            else {
                Log.e("AdminRepositoryImpl", "Error getting ticket: HTTP ${response.code()} ${response.errorBody()?.string()}")
                Ticket(0, "", List(0) { TicketMessage("") })
            }
        } catch (e: Exception) {
            Log.e("AdminRepositoryImpl", "Exception when calling API", e)
            Ticket(0, "", List(0) {TicketMessage("")})
        }
    }

    override suspend fun deleteTicketById(deleteTicketDTO: DeleteTicketDTO): Boolean {
        return try {
            val response = adminApiService.deleteTicketById(deleteTicketDTO.ticketId)
            if(response.isSuccessful) {
                Log.d("AdminRepositoryImpl", "Ticket deleted")
                true
            }
            else {
                Log.e("AdminRepositoryImpl", "Error deleting ticket: HTTP ${response.code()} ${response.message()}")
                false
            }
        } catch (e: Exception) {
            Log.e("AdminRepositoryImpl", "Exception when calling API", e)
            false
        }
    }

    override suspend fun deleteProductById(productId: Int): String {
        return try {
            val response = adminApiService.deleteProductById(productId)
            if(response.isSuccessful) {
                Log.d("AdminRepositoryImpl", "Product deleted: ${response.body()}")
                response.body() ?: String()
            }
            else {
                Log.e("AdminRepositoryImpl", "Error deleting product: HTTP ${response.code()} ${response.message()}")
                String()
            }
            String()
        } catch (e: Exception) {
            Log.e("AdminRepositoryImpl", "Exception when calling API", e)
            String()
        }
    }
}
