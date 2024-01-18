package com.chopecard.data.network

import com.chopecard.data.model.ReserveDTO
import com.chopecard.data.model.UserDTO
import com.chopecard.domain.models.Store
import com.chopecard.domain.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiService {

    @POST("/v1/user")
    fun createUser(@Body userDTO: UserDTO): Call<String>

    @GET("/v1/user/{userId}")
    fun getUserById(@Path("userId") userId: Int): Call<User>

    @DELETE("/v1/user/{userId}")
    fun deleteUserById(@Path("userId") userId: Int): Call<String>

    @GET("/v1/user/{userId}/reserves")
    fun getReservesByUserId(@Path("userId") userId: Int): Call<Store>

    @GET("/v1/user/{userId}/reserves/{reservesId}")
    fun getReservesByIdAndByUserId(@Path("userId") userId: Int, @Path("reservesId") reservesId: Int): Call<Store>
}