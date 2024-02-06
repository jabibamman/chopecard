package com.chopecard.domain.usecases

import com.chopecard.data.repository.StoreRepository
import com.chopecard.domain.models.UserReservation

class GetReservationsUseCase(private val repository: StoreRepository) {
    suspend fun execute(storeId: Int): List<UserReservation> {
        return repository.getReserves(storeId)
    }
}
