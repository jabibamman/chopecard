package com.chopecard.domain.usecases

import com.chopecard.data.model.CreateProductDTO
import com.chopecard.data.repository.StoreRepository

class UpdateProductUseCase(private val repository: StoreRepository) {
    suspend fun execute(createProductDTO: CreateProductDTO) {
        repository.createProduct(createProductDTO)
    }
}