// ProductRepository.kt
package com.chopecard.data.repository

import com.chopecard.domain.models.Product

interface ProductRepository {

    // Retrieves a list of all products
    suspend fun getProducts(): List<Product>

    // Retrieves details of a specific product by product ID
    suspend fun getProduct(productId: Int): Product
}
