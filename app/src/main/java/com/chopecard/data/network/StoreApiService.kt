package com.chopecard.data.network

import com.chopecard.data.model.ProductStoreDTO
import com.chopecard.data.model.ReserveDTO
import com.chopecard.data.model.StoreDTO
import com.chopecard.domain.models.Display
import com.chopecard.domain.models.ProductStore
import com.chopecard.domain.models.Store
import com.chopecard.domain.models.UserReservation
import retrofit2.Response
import retrofit2.http.*

interface StoreApiService {

    @GET("/v1/stores")
    suspend fun getStores(): Response<List<Store>>

    @POST("/v1/stores")
    suspend fun  createStore(@Body storeDTO: StoreDTO): Response<String>

    @POST("/v1/stores/{storeId}/user/{userId}/reserve")
    suspend fun  reserveProduct(@Path("storeId") storeId: Int, @Path("userId") userId: Int, @Body reserveDTO: ReserveDTO): Response<Void>

    @GET("/v1/stores/{storeId}/products/{productId}")
    suspend fun  getProduct(@Path("storeId") storeId: Int, @Path("productId") productId: Int): Response<ProductStore>

    @POST("/v1/stores/{storeId}/products/{productId}")
    suspend fun createProduct(
        @Path("storeId") storeId: Int,
        @Path("productId") productId: Int,
        @Body productStoreDTO: ProductStoreDTO
    ): Response<Void>

    @DELETE("/v1/stores/{storeId}/products/{productId}")
    suspend fun deleteProduct(
        @Path("storeId") storeId: Int,
        @Path("productId") productId: Int,
    ): Response<Void>

    @PATCH("/v1/stores/{storeId}/products/{productId}")
    suspend fun updateProduct(
        @Path("storeId") storeId: Int,
        @Path("productId") productId: Int,
        @Body productStoreDTO: ProductStoreDTO
    ): Response<Void>

    @GET("/v1/stores/{storeId}")
    suspend fun getStoresById(@Path("storeId") storeId: Int): Response<Store>

    @DELETE("/v1/stores/{storeId}")
    suspend fun  deleteStore(@Path("storeId") storeId: Int): Response<String>

    @GET("/v1/stores/{storeId}/reserves")
    suspend fun  getReserves(@Path("storeId") storeId: Int): Response<List<UserReservation>>

    @GET("/v1/stores/{storeId}/products")
    suspend fun  getStoreProducts(@Path("storeId") storeId: Int): Response<List<ProductStore>>

    @GET("/v1/displays/{storeId}")
    suspend fun  getDisplay(@Path("storeId") storeId: Int): Response<Display>

    @DELETE("/v1/stores/{storeId}/user/{userId}/reservation/{reserveId}/unreserve")
    suspend fun  unreserveProduct(@Path("storeId") storeId: Int, @Path("userId") userId: Int, @Path("reserveId") reserveId: Int): Response<String>
}
