package com.chopecard.data.repository

import com.chopecard.data.model.Card
import com.chopecard.data.model.CardSet
import com.chopecard.data.network.CardApiService

class CardRepositoryImpl(private val cardApiService: CardApiService) : CardRepository {

    override suspend fun getCardByName(name: String): List<Card> {
        return try {
            val response = cardApiService.getCardInfo(name)
            response.data
        } catch (e: Exception) {
            emptyList()
        }
    }
    override suspend fun getCardsByType(type: String): List<Card> {
        return try {
            val response = cardApiService.getCardsInfoByType(type)
            response.data
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getCardSetByCode(setCode: String): List<CardSet> {
        return try {
            val response = cardApiService.getCardSetInfo(setCode)
            response.data
        } catch (e: Exception) {
            emptyList()
        }
    }

}
