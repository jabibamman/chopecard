package com.chopecard.data.repository

import com.chopecard.data.model.UserDTO
import com.chopecard.domain.models.Store
import com.chopecard.domain.models.User

interface UserRepository {

    // Creates a new user record
    suspend fun createUser(userDTO: UserDTO): User

    // Retrieves details of a specific User by user ID
    suspend fun getUser(userId: Int): User

    // Deletes a user from the system
    suspend fun deleteUser(userId: Int): String

    // Retrieves details of a store of a User by user ID
    suspend fun getReservesByUserId(userId: Int): Store

    // Retrieves details of a store of a User by user ID and reserveId
    suspend fun getReservesByIdAndByUserId(userId: Int, reserveId: Int): Store
}