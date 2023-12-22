package com.chopecard.data.repository

import com.chopecard.data.model.Card
import com.chopecard.data.model.CardSet

interface CardRepository {
    suspend fun getCardByName(name: String): List<Card>
    suspend fun getCardsByType(type: String): List<Card>
    suspend fun getCardSetByCode(setCode: String): List<CardSet>
}
