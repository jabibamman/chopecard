package com.chopecard.domain.usecases

import com.chopecard.data.repository.StoreRepository

class UnreserveProductUseCase(private val repository: StoreRepository) {
    suspend fun execute(storeId: Int, userId: Int, reserveId: Int): Boolean {
        return repository.unreserveProduct(storeId, userId, reserveId)
    }
}
