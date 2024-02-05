package com.chopecard.data.network

import com.chopecard.domain.models.Product
import com.chopecard.domain.models.Ticket
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AdminApiService {
    @POST("/v1/admin/tickets/create")
    suspend fun createTicket(@Body ticket: Ticket): Response<String>

    @POST("/v1/admin/products/create")
    suspend fun createProduct(@Body product: Product): Response<String>

    @GET("/v1/admin/tickets")
    suspend fun getTickets(): Response<List<Ticket>>

    @GET("/v1/admin/tickets/{ticketId}")
    suspend fun getTicketById(@Path("ticketId") ticketId: Int): Response<Ticket>

    @DELETE("/v1/admin/tickets/{ticketId}")
    suspend fun deleteTicketById(@Path("ticketId") ticketId: Int): Response<String>

    @DELETE("/v1/admin/products/{productId}")
    suspend fun deleteProductById(@Path("productId") productId: Int): Response<String>
}