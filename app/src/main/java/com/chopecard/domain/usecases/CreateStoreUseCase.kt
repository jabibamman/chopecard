package com.chopecard.domain.usecases

import com.chopecard.data.model.StoreDTO
import com.chopecard.data.repository.StoreRepository

class CreateStoreUseCase(private val repository: StoreRepository) {
    suspend fun execute(storeDTO: StoreDTO): String {
        return repository.createStore(storeDTO)
    }
}
