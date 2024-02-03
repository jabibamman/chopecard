package com.chopecard.domain.usecases

import com.chopecard.data.model.UserDTO
import com.chopecard.data.repository.UserRepository
import com.chopecard.domain.models.User

class CreateUserUseCase(private val repository: UserRepository) {
    suspend fun execute(userDTO: UserDTO): User {
        return repository.createUser(userDTO)
    }
}
