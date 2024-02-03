package com.chopecard.domain.usecases

import com.chopecard.data.repository.UserRepository
import com.chopecard.domain.models.User

class GetUserUseCase(private val repository: UserRepository) {
    suspend fun execute(userId: Int): User {
        return repository.getUser(userId)
    }
}