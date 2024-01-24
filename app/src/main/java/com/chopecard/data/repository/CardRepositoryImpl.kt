package com.chopecard.data.repository

import com.chopecard.data.model.Card
import com.chopecard.data.model.CardImage
import com.chopecard.data.model.CardPrice
import com.chopecard.data.model.CardSet
import com.chopecard.data.network.CardApiService
import com.chopecard.domain.models.Display
import com.chopecard.domain.models.TicketMessage
import retrofit2.Call

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

    override suspend fun getCards(): List<Card> {
        val cardDTOs: Call<List<Card>> = cardApiService.getCards()
        return try {
            val response = cardDTOs.execute()
            response.body() ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getCard(cardId: Int): Card {
        val cardDTO = cardApiService.getCardById(cardId)
        return try {
            val response = cardDTO.execute()
            response.body() ?: Card(0, "", "","","",0,0,0,"","",List<CardSet>(0) { CardSet("","","","","")}, List<CardImage>(0) { CardImage(0, "","","")}, List<CardPrice>(0) { CardPrice("", "", "","","")} )
        } catch (e: Exception) {
            Card(0, "", "","","",0,0,0,"","",List<CardSet>(0) { CardSet("","","","","")}, List<CardImage>(0) { CardImage(0, "","","")}, List<CardPrice>(0) { CardPrice("", "", "","","")} )
        }
    }

}
