package com.chopecard.domain.usecases

import com.chopecard.data.repository.ProductRepository

class GetProductsUseCase(private val repository: ProductRepository) {
    suspend fun execute() = repository.getProducts()
}
