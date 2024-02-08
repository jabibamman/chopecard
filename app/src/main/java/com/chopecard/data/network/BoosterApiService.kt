package com.chopecard.data.network

import com.chopecard.domain.models.Booster
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BoosterApiService {
    @GET("/v1/boosters")
    fun getBoosters(): Call<List<Booster>>

    @GET("/v1/boosters/{boosterId}")
    fun getBoosterById(@Path("boosterId") boosterId: Int): Call<Booster>
}