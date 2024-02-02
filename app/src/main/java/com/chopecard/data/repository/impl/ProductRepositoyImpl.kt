package com.chopecard.data.repository.impl

import com.chopecard.data.network.ProductApiService
import com.chopecard.data.repository.ProductRepository
import com.chopecard.domain.models.Product
import retrofit2.Call


class ProductRepositoyImpl(private val productApiService: ProductApiService) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        val productDTOs: Call<List<Product>> = productApiService.getProducts()
        return try {
            val response = productDTOs.execute()
            response.body() ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getProduct(productId: Int): Product {
        val productDTO = productApiService.getProductById(productId)
        return try {
            val response = productDTO.execute()
            response.body() ?: Product(0, "", "", 0f, 0f)
        } catch (e: Exception) {
            Product(0, "", "", 0f, 0f)
        }
    }
}
