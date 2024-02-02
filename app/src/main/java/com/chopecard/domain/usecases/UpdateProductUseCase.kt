package com.chopecard.domain.usecases

import com.chopecard.data.model.UpdateProductDTO
import com.chopecard.data.repository.StoreRepository

class UpdateProductUseCase(private val repository: StoreRepository) {
    suspend fun execute(updateProductDTO: UpdateProductDTO) {
        repository.updateProductStore(updateProductDTO)
    }
}