package com.chopecard.data.network

import com.chopecard.domain.models.Product
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("/v1/products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("/v1/products/{productId}")
    fun getProductById(@Path("productId") productId: Int): Call<Product>
}