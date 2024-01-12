package com.chopecard.domain.usecases

import com.chopecard.data.repository.StoreRepository
import com.chopecard.domain.models.Store

class GetStoresUseCase(private val storeRepository: StoreRepository) {
    suspend operator fun invoke(): List<Store> {
        return storeRepository.getStores()
    }
}