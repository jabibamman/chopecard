package com.chopecard.data.repository

import com.chopecard.data.model.LoginUserDTO
import com.chopecard.data.model.UserDTO
import com.chopecard.domain.models.Store
import com.chopecard.domain.models.User
import com.chopecard.domain.models.UserReservation

interface UserRepository {

    // Creates a new user record
    suspend fun createUser(userDTO: UserDTO): User

    // Login the user
    suspend fun loginUser(loginUserDTO: LoginUserDTO): User

    // Retrieves details of a specific User by user ID
    suspend fun getUser(userId: Int): User

    // Retrieves details of a specific User by email
    suspend fun getUser(email: String): User

    // Deletes a user from the system
    suspend fun deleteUser(userId: Int): String

    // Retrieves details of a store of a User by user ID
    suspend fun getReservesByUserId(userId: Int): List<UserReservation>

    // Retrieves details of a store of a User by user ID and reserveId
    suspend fun getReservesByIdAndByUserId(userId: Int, reserveId: Int): Store
}