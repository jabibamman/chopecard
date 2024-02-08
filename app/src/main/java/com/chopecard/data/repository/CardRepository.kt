package com.chopecard.data.repository

import com.chopecard.data.model.Card
import com.chopecard.data.model.CardSet

interface CardRepository {
    suspend fun getYugiohCardByName(name: String): List<Card>
    suspend fun getYugiohCardsByType(type: String): List<Card>
    suspend fun getYugiohCardSetByCode(setCode: String): List<CardSet>

    // Retrieves a list of all boosters
    suspend fun getCards(): List<Card>

    // Retrieves details of a specific boosters by boosters ID
    suspend fun getCard(cardId: Int): Card
}
