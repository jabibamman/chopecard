package com.chopecard.domain.usecases

import com.chopecard.data.repository.StoreRepository
import com.chopecard.domain.models.Store

class GetStoresUseCase(private val repository: StoreRepository) {
    suspend fun execute(): List<Store> {
        try {
            return repository.getStores()
        } catch (e: Exception) {
            throw e
        }
    }
}
