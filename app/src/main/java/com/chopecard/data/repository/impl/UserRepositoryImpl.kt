package com.chopecard.data.repository.impl

import com.chopecard.data.model.UserDTO
import com.chopecard.data.network.UserApiService
import com.chopecard.data.repository.UserRepository
import com.chopecard.domain.models.Product
import com.chopecard.domain.models.ProductStore
import com.chopecard.domain.models.Store
import com.chopecard.domain.models.User
import com.chopecard.domain.models.UserReservation

class UserRepositoryImpl(private val userApiService: UserApiService) : UserRepository {

    override suspend fun createUser(userDTO: UserDTO): String {
        val userDTO = userApiService.createUser(userDTO)
        return try {
            val response = userDTO.execute()
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun getUser(userId: Int): User {
        val userDTO = userApiService.getUserById(userId)
        return try {
            val response = userDTO.execute()
            response.body() ?: User(0, "", "", List<UserReservation>(0) { UserReservation("", "", 0, 0) } )
        } catch (e: Exception) {
            User(0, "", "", List<UserReservation>(0) {UserReservation("", "", 0, 0)} )
        }
    }

    override suspend fun deleteUser(userId: Int): String {
        val userDTO = userApiService.deleteUserById(userId)
        return try {
            val response = userDTO.execute()
            response.body() ?: String()
        } catch (e: Exception) {
            String()
        }
    }

    override suspend fun getReservesByUserId(userId: Int): Store {
        val reserveDTO = userApiService.getReservesByUserId(userId)
        return try {
            val response = reserveDTO.execute()
            response.body() ?: Store(0,"","",List<ProductStore>(0) {ProductStore(0, Product(0, "", "", 0f, 0f), 0, 0f)})
        } catch (e: Exception) {
            Store(0,"","",List<ProductStore>(0) {ProductStore(0, Product(0, "", "", 0f, 0f), 0, 0f)})
        }
    }

    override suspend fun getReservesByIdAndByUserId(userId: Int, reserveId: Int): Store {
        val reserveDTO = userApiService.getReservesByIdAndByUserId(userId, reserveId)
        return try {
            val response = reserveDTO.execute()
            response.body() ?: Store(0,"","",List<ProductStore>(0) {ProductStore(0, Product(0, "", "", 0f, 0f), 0, 0f)})
        } catch (e: Exception) {
            Store(0,"","",List<ProductStore>(0) {ProductStore(0, Product(0, "", "", 0f, 0f), 0, 0f)})
        }
    }


}
