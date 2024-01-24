package com.chopecard.data.network

import com.chopecard.domain.models.Display
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DisplayApiService {
    @GET("/v1/displays")
    fun getDisplays(): Call<List<Display>>

    @GET("/v1/displays/{displayId}")
    fun getDisplayById(@Path("displayId") displayId: Int): Call<Display>
}