// GetProductDetailUseCase.kt
package com.chopecard.domain.usecases

import com.chopecard.data.repository.ProductRepository
import com.chopecard.domain.models.Product

class GetProductDetailUseCase(private val productRepository: ProductRepository) {
    suspend fun execute(productId: Int): Product {
        return productRepository.getProduct(productId)
    }
}
