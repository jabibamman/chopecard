package com.chopecard.data.repository.impl

import com.chopecard.data.network.BoosterApiService
import com.chopecard.data.repository.BoosterRepository
import com.chopecard.domain.models.Booster
import retrofit2.Call


class BoosterRepositoyImpl(private val boosterApiService: BoosterApiService) : BoosterRepository {
    override suspend fun getBoosters(): List<Booster> {
        val boosterDTOs: Call<List<Booster>> = boosterApiService.getBoosters()
        return try {
            val response = boosterDTOs.execute()
            response.body() ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getBooster(boosterId: Int): Booster {
        val boosterDTO = boosterApiService.getBoosterById(boosterId)
        return try {
            val response = boosterDTO.execute()
            response.body() ?: Booster(0, "", "", 0f, 0f)
        } catch (e: Exception) {
            Booster(0, "", "", 0f, 0f)
        }
    }
}
