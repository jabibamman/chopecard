package com.chopecard.domain.usecases

import com.chopecard.data.repository.StoreRepository
import com.chopecard.domain.models.ProductStore

class GetStoreProductsUseCase(private val repository: StoreRepository) {
    suspend fun execute(storeId: Int): List<ProductStore> {
        return repository.getStoreProducts(storeId)
    }
}
