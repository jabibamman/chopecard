package com.chopecard.domain.usecases

import com.chopecard.data.repository.StoreRepository

class DeleteProductUseCase(private val repository: StoreRepository) {
    suspend fun execute(storeId: Int, productId: Int): String {
        return repository.deleteStore(storeId)
    }
}