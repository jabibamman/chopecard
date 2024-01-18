package com.chopecard.data.network

import com.chopecard.data.model.ProductStoreDTO
import com.chopecard.data.model.ReserveDTO
import com.chopecard.data.model.StoreDTO
import com.chopecard.domain.models.Display
import com.chopecard.domain.models.ProductStore
import com.chopecard.domain.models.Store
import com.chopecard.domain.models.UserReservation
import retrofit2.Call
import retrofit2.http.*

interface StoreApiService {

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

    @GET("/v1/stores/{storeId}")
    fun getStoresById(@Path("storeId") storeId: Int): Call<Store>

    @DELETE("/v1/stores/{storeId}")
    fun deleteStore(@Path("storeId") storeId: Int): Call<String>

    @GET("/v1/stores/{storeId}/reserves")
    fun getReserves(@Path("storeId") storeId: Int): Call<List<UserReservation>>

    @GET("/v1/stores/{storeId}/products")
    fun getStoreProducts(@Path("storeId") storeId: Int): Call<List<ProductStore>>

    @GET("/v1/displays/{storeId}")
    fun getDisplay(@Path("storeId") storeId: Int): Call<Display>

    @DELETE("/v1/stores/{storeId}/user/{userId}/reservation/{reserveId}/unreserve")
    fun unreserveProduct(@Path("storeId") storeId: Int, @Path("userId") userId: Int, @Path("reserveId") reserveId: Int): Call<String>
}
