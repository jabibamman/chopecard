package com.chopecard.domain.usecases

import com.chopecard.data.model.ReserveDTO
import com.chopecard.data.repository.StoreRepository

class ReserveProductUseCase(private val repository: StoreRepository) {
    suspend fun execute(storeId: Int, userId: Int, reserveDTO: ReserveDTO): String {
        return repository.reserveProduct(storeId, userId, reserveDTO)
    }
}
