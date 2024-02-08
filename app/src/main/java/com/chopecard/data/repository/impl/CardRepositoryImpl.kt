package com.chopecard.data.repository.impl

import android.util.Log
import com.chopecard.data.model.Card
import com.chopecard.data.model.CardImage
import com.chopecard.data.model.CardPrice
import com.chopecard.data.model.CardSet
import com.chopecard.data.network.CardApiService
import com.chopecard.data.repository.CardRepository
import retrofit2.Call

class CardRepositoryImpl(private val cardApiService: CardApiService) : CardRepository {

    override suspend fun getYugiohCardByName(name: String): List<Card> {
        return try {
            val response = cardApiService.getYugiohCardInfo(name)
            if (response.isSuccessful) {
                Log.d("CardRepositoryImpl", "Cards: ${response.body().toString()}")
                response.body()?.data ?: emptyList()
            } else {
                Log.e("CardRepositoryImpl", "Error getting cards: HTTP ${response.code()} ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("CardRepositoryImpl", "Exception when calling API", e)
            emptyList()
        }
    }
    override suspend fun getYugiohCardsByType(type: String): List<Card> {
        return try {
            val response = cardApiService.getYugiohCardsInfoByType(type)
            if (response.isSuccessful) {
                Log.d("CardRepositoryImpl", "Cards: ${response.body().toString()}")
                response.body()?.data ?: emptyList()
            } else {
                Log.e("CardRepositoryImpl", "Error getting cards: HTTP ${response.code()} ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("CardRepositoryImpl", "Exception when calling API", e)
            emptyList()
        }
    }

    override suspend fun getYugiohCardSetByCode(setCode: String): List<CardSet> {
        return try {
            val response = cardApiService.getYugiohCardSetInfo(setCode)
            if (response.isSuccessful) {
                Log.d("CardRepositoryImpl", "Card Sets: ${response.body().toString()}")
                response.body()?.data ?: emptyList()
            } else {
                Log.e("CardRepositoryImpl", "Error getting card sets: HTTP ${response.code()} ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("CardRepositoryImpl", "Exception when calling API", e)
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
            response.body() ?: Card(0, "", "","","",0,0,0,"","",List(0) { CardSet("","","","","")}, List(0) { CardImage(0, "","","")}, List(0) { CardPrice("", "", "","","")} )
        } catch (e: Exception) {
            Card(0, "", "","","",0,0,0,"","",List(0) { CardSet("","","","","")}, List(0) { CardImage(0, "","","")}, List(0) { CardPrice("", "", "","","")} )
        }
    }

}
