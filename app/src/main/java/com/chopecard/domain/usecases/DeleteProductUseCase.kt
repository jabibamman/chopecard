package com.chopecard.domain.usecases

import com.chopecard.data.model.DeleteProductDTO
import com.chopecard.data.repository.StoreRepository

class DeleteProductUseCase(private val repository: StoreRepository) {
    suspend fun execute(deleteProductDTO: DeleteProductDTO) {
        repository.deleteProductStore(deleteProductDTO)
    }
}
