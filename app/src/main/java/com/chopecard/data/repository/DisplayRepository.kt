package com.chopecard.data.repository

import com.chopecard.domain.models.Display

interface DisplayRepository {

    // Retrieves a list of all displays
    suspend fun getDisplays(): List<Display>

    // Retrieves details of a specific display by display ID
    suspend fun getDisplay(displayId: Int): Display
}