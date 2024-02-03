package com.chopecard.data.repository

import com.chopecard.data.model.Card
import com.chopecard.data.model.CardSet
import com.chopecard.domain.models.Booster

interface CardRepository {
    suspend fun getCardByName(name: String): List<Card>
    suspend fun getCardsByType(type: String): List<Card>
    suspend fun getCardSetByCode(setCode: String): List<CardSet>

    // Retrieves a list of all boosters
    suspend fun getCards(): List<Card>

    // Retrieves details of a specific boosters by boosters ID
    suspend fun getCard(cardId: Int): Card
}
