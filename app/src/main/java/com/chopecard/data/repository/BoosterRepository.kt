package com.chopecard.data.repository

import com.chopecard.domain.models.Booster

interface BoosterRepository {

    // Retrieves a list of all boosters
    suspend fun getBoosters(): List<Booster>

    // Retrieves details of a specific boosters by boosters ID
    suspend fun getBooster(boosterId: Int): Booster
}