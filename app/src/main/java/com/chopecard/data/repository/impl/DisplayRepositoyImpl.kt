package com.chopecard.data.repository.impl

import com.chopecard.data.network.DisplayApiService
import com.chopecard.data.repository.DisplayRepository
import com.chopecard.domain.models.Display
import retrofit2.Call


class DisplayRepositoyImpl(private val displayApiService: DisplayApiService) : DisplayRepository {
    override suspend fun getDisplays(): List<Display> {
        val displayDTOs: Call<List<Display>> = displayApiService.getDisplays()
        return try {
            val response = displayDTOs.execute()
            response.body() ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getDisplay(displayId: Int): Display {
        val displayDTO = displayApiService.getDisplayById(displayId)
        return try {
            val response = displayDTO.execute()
            response.body() ?: Display(0, "", "", 0f, 0f)
        } catch (e: Exception) {
            Display(0, "", "", 0f, 0f)
        }
    }
}
