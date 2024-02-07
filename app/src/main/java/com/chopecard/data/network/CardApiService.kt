package com.chopecard.data.network

import com.chopecard.data.model.Card
import com.chopecard.data.model.CardInfoResponse
import com.chopecard.data.model.CardSetInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface CardApiService {
    @GET("/v1/yugioh/cardsByName")
    suspend fun getYugiohCardInfo(@Query("name") cardName: String): CardInfoResponse

    @GET("/v1/yugioh/cardsByType")
    suspend fun getYugiohCardsInfoByType(@Query("type") cardType: String): CardInfoResponse

    @GET("/v1/yugioh/cardSetInfo")
    suspend fun getYugiohCardSetInfo(@Query("setcode") setCode: String): CardSetInfoResponse

    @GET("/v1/cards")
    fun getCards(): Call<List<Card>>

    @GET("/v1/cards/{cardId}")
    fun getCardById(@Path("cardId") cardId: Int): Call<Card>
}
