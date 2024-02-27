package com.chopecard.domain.usecases

import android.util.Log
import com.chopecard.data.repository.UserRepository
import com.chopecard.domain.models.UserReservation

class GetUserReservationsUseCase(private val repository: UserRepository) {
    suspend fun execute(userId: Int): List<UserReservation> {
        return repository.getReservesByUserId(userId)
    }
}
