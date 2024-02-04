package com.chopecard.domain.usecases

import com.chopecard.data.model.CreateProductDTO
import com.chopecard.data.repository.StoreRepository

class AddProductUseCase(private val repository: StoreRepository) {
    suspend fun execute(createProductDTO: CreateProductDTO) : Boolean {
        return repository.createProduct(createProductDTO)
    }

}
