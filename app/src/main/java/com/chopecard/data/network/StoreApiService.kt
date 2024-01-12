package com.chopecard.data.network

import com.chopecard.data.model.PatchProductDTO
import com.chopecard.data.model.ProductStoreDTO
import com.chopecard.data.model.ReserveDTO
import com.chopecard.data.model.StoreDTO
import com.chopecard.data.model.UserDTO
import com.chopecard.domain.models.Booster
import com.chopecard.domain.models.Card
import com.chopecard.domain.models.Display
import com.chopecard.domain.models.Product
import com.chopecard.domain.models.ProductStore
import com.chopecard.domain.models.Store
import com.chopecard.domain.models.Ticket
import com.chopecard.domain.models.UserReservation
import com.chopecard.domain.models.Users
import retrofit2.Call
import retrofit2.http.*

interface StoreApiService {

    @POST("/v1/user")
    fun createUser(@Body userDTO: UserDTO): Call<String>

    @GET("/v1/stores")
    fun getStores(): Call<List<Store>>

    @POST("/v1/stores")
    fun createStore(@Body storeDTO: StoreDTO): Call<String>

    @POST("/v1/stores/{storeId}/user/{userId}/reserve")
    fun reserveProduct(@Path("storeId") storeId: Int, @Path("userId") userId: Int, @Body reserveDTO: ReserveDTO): Call<String>

    @GET("/v1/stores/{storeId}/products/{productId}")
    fun getProduct(@Path("storeId") storeId: Int, @Path("productId") productId: Int): Call<ProductStore>

    @POST("/v1/stores/{storeId}/products/{productId}")
    fun createProduct(@Path("storeId") storeId: Int, @Path("productId") productId: Int, @Body productStoreDTO: ProductStoreDTO): Call<String>

    @POST("/v1/admin/tickets/create")
    fun createTicket(@Body ticket: Ticket): Call<Long>

    @POST("/v1/admin/products/create")
    fun createProduct_1(@Body product: Product): Call<Long>

    @DELETE("/v1/stores/{storeId}/products/{productsId}")
    fun deleteProduct(@Path("storeId") storeId: Long, @Path("productsId") productsId: Long): Call<String>

    @PATCH("/v1/stores/{storeId}/products/{productsId}")
    fun updateProduct(@Path("storeId") storeId: Long, @Path("productsId") productsId: Long, @Body patchProductDTO: PatchProductDTO): Call<ProductStore>

    @GET("/v1/user/{userId}")
    fun getUserById(@Path("userId") userId: Long): Call<Users>

    @DELETE("/v1/user/{userId}")
    fun deleteUser(@Path("userId") userId: Long): Call<String>

    @GET("/v1/user/{userId}/reserves")
    fun getReservations(@Path("userId") userId: Long): Call<List<UserReservation>>

    @GET("/v1/user/{userId}/reserves/{reserveId}")
    fun getReservation(@Path("userId") userId: Long, @Path("reserveId") reserveId: Long): Call<UserReservation>

    @GET("/v1/stores/{storeId}")
    fun getStoresById(@Path("storeId") storeId: Int): Call<Store>

    @DELETE("/v1/stores/{storeId}")
    fun deleteStore(@Path("storeId") storeId: Int): Call<String>

    @GET("/v1/stores/{storeId}/user/{userId}/reservers")
    fun getReservers(@Path("storeId") storeId: Long, @Path("userId") userId: Long): Call<List<UserReservation>>

    @GET("/v1/stores/{storeId}/reserves")
    fun getReserves(@Path("storeId") storeId: Int): Call<List<UserReservation>>

    @GET("/v1/stores/{storeId}/products")
    fun getStoreProducts(@Path("storeId") storeId: Int): Call<List<ProductStore>>

    @GET("/v1/products")
    fun getProducts_1(): Call<List<Product>>

    @GET("/v1/products/{productId}")
    fun getProduct_1(@Path("productId") productId: Long): Call<Product>

    @GET("/v1/displays")
    fun getDisplays(): Call<List<Display>>

    @GET("/v1/displays/{storeId}")
    fun getDisplay(@Path("storeId") storeId: Int): Call<Display>

    @GET("/v1/cards")
    fun getCards(): Call<List<Card>>

    @GET("/v1/cards/{productId}")
    fun getCard(@Path("productId") productId: Long): Call<Card>

    @GET("/v1/boosters")
    fun getBoosters(): Call<List<Booster>>

    @GET("/v1/boosters/{productId}")
    fun getBoosters_1(@Path("productId") productId: Long): Call<Booster>

    @GET("/v1/admin/tickets")
    fun getAllTickets(): Call<List<Ticket>>

    @GET("/v1/admin/tickets/{ticketId}")
    fun getTicketById(@Path("ticketId") ticketId: Long): Call<Ticket>

    @DELETE("/v1/admin/tickets/{ticketId}")
    fun deleteTicket(@Path("ticketId") ticketId: Long): Call<Void>

    @DELETE("/v1/stores/{storeId}/user/{userId}/reservation/{reserveId}/unreserve")
    fun unreserveProduct(@Path("storeId") storeId: Int, @Path("userId") userId: Int, @Path("reserveId") reserveId: Int): Call<String>

    @DELETE("/v1/admin/products/{productId}")
    fun deleteProduct_1(@Path("productId") productId: Long): Call<Void>
}
