package com.chopecard.data.repository.impl

import android.util.Log
import com.chopecard.data.model.LoginUserDTO
import com.chopecard.data.model.UserDTO
import com.chopecard.data.network.UserApiService
import com.chopecard.data.repository.UserRepository
import com.chopecard.domain.models.Product
import com.chopecard.domain.models.ProductStore
import com.chopecard.domain.models.Store
import com.chopecard.domain.models.User
import retrofit2.HttpException

class UserRepositoryImpl(private val userApiService: UserApiService) : UserRepository {

    override suspend fun createUser(userDTO: UserDTO): User {
        val response = userApiService.createUser(userDTO)
        if (response.isSuccessful) {
            Log.d("UserRepositoryImpl", "User created: ${response.body()}")
            return response.body() ?: throw Exception("User data was null")
        } else {
            Log.e("UserRepositoryImpl", "Error creating user: HTTP ${response.code()} ${response.errorBody()?.string()}")
            throw Exception("API call failed with error: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun loginUser(loginUserDTO: LoginUserDTO) : User {
        val response = userApiService.login(loginUserDTO)
        if (response.isSuccessful) {
            Log.d("UserRepositoryImpl", "User logged: ${response.body()}")
            return response.body() ?: throw Exception("User data was null")
        } else {
            Log.e("UserRepositoryImpl", "Error logging user: HTTP ${response.code()} ${response.errorBody()?.string()}")
            throw Exception("API call failed with error: ${response.errorBody()?.string()}")
        }
    }


    override suspend fun getUser(userId: Int): User {
        try {
            val response = userApiService.getUserById(userId)
            if (response.isSuccessful) {
                val user = response.body()
                Log.d("UserRepositoryImpl", "User: $user")
                return user ?: User(0, "", "", emptyList(), "USER")
            } else {
                Log.e("UserRepositoryImpl", "Error getting user: HTTP ${response.code()} ${response.errorBody()?.string()}")
                throw Exception("Error getting user: HTTP ${response.code()} ${response.errorBody()?.string()}")
            }
        } catch (e: HttpException) {
            Log.e("UserRepositoryImpl", "HttpException when calling API", e)
            throw e
        } catch (e: Exception) {
            Log.e("UserRepositoryImpl", "Exception when calling API", e)
            throw e
        }
    }

    override suspend fun getUser(email: String): User {
        try {
            val response = userApiService.getUserByEmail(email)
            if (response.isSuccessful) {
                val user = response.body()
                Log.d("UserRepositoryImpl", "User: $user")
                return user ?: User(0, "", "", emptyList(), "USER")
            } else {
                Log.e("UserRepositoryImpl", "Error getting user: HTTP ${response.code()} ${response.errorBody()?.string()}")
                throw Exception("Error getting user: HTTP ${response.code()} ${response.errorBody()?.string()}")
            }
        } catch (e: HttpException) {
            Log.e("UserRepositoryImpl", "HttpException when calling API", e)
            throw e
        } catch (e: Exception) {
            Log.e("UserRepositoryImpl", "Exception when calling API", e)
            throw e
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
            response.body() ?: Store(0,"","",List(0) {ProductStore(0, Product(0, "", "", 0f, 0f), 0, 0f)})
        } catch (e: Exception) {
            Store(0,"","",List(0) {ProductStore(0, Product(0, "", "", 0f, 0f), 0, 0f)})
        }
    }

    override suspend fun getReservesByIdAndByUserId(userId: Int, reserveId: Int): Store {
        val reserveDTO = userApiService.getReservesByIdAndByUserId(userId, reserveId)
        return try {
            val response = reserveDTO.execute()
            response.body() ?: Store(0,"","",List(0) {ProductStore(0, Product(0, "", "", 0f, 0f), 0, 0f)})
        } catch (e: Exception) {
            Store(0,"","",List(0) {ProductStore(0, Product(0, "", "", 0f, 0f), 0, 0f)})
        }
    }


}
