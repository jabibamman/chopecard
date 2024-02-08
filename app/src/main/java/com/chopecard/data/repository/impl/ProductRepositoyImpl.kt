package com.chopecard.data.repository.impl

import android.util.Log
import com.chopecard.data.network.ProductApiService
import com.chopecard.data.repository.ProductRepository
import com.chopecard.domain.models.Product


class ProductRepositoyImpl(private val productApiService: ProductApiService) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return try {
            val response = productApiService.getProducts()
            if (response.isSuccessful) {
                Log.d("ProductRepositoryImpl", "Products: ${response.body().toString()}")
            } else {
                Log.e("ProductRepositoryImpl", "Error getting products: HTTP ${response.code()} ${response.errorBody()?.string()}")
            }
            response.body() ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getProduct(productId: Int): Product {
        val productDTO = productApiService.getProductById(productId)
        return try {
            val response = productDTO.execute()
            response.body() ?: Product(0, "", "", 0f, 0f, "")
        } catch (e: Exception) {
            Product(0, "", "", 0f, 0f, "")
        }
    }
}
