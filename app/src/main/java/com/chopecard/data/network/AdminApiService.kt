package com.chopecard.data.network

import com.chopecard.domain.models.Product
import com.chopecard.domain.models.Ticket
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AdminApiService {
    @POST("/v1/admin/tickets/create")
    fun createTicket(@Body ticket: Ticket): Call<String>

    @POST("/v1/admin/products/create")
    fun createProduct(@Body product: Product): Call<String>

    @POST("/v1/admin/tickets")
    fun getTickets(): Call<List<Ticket>>

    @GET("/v1/admin/tickets/{ticketId}")
    fun getTicketById(@Path("ticketId") ticketId: Int): Call<Ticket>

    @DELETE("/v1/admin/tickets/{ticketId}")
    fun deleteTicketById(@Path("ticketId") ticketId: Int): Call<String>

    @DELETE("/v1/admin/products/{productId}")
    fun deleteProductById(@Path("productId") productId: Int): Call<String>
}