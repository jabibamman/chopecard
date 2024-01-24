package com.chopecard.data.network

import com.chopecard.data.model.Card
import com.chopecard.data.model.CardInfoResponse
import com.chopecard.data.model.CardSetInfoResponse
import com.chopecard.domain.models.Display
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface CardApiService {
    @GET("cardinfo.php")
    suspend fun getCardInfo(@Query("name") cardName: String): CardInfoResponse

    @GET("cardinfo.php")
    suspend fun getCardsInfoByType(@Query("type") cardType: String): CardInfoResponse

    @GET("cardsetsinfo.php")
    suspend fun getCardSetInfo(@Query("setcode") setCode: String): CardSetInfoResponse

    @GET("/v1/cards")
    fun getCards(): Call<List<Card>>

    @GET("/v1/cards/{cardId}")
    fun getCardById(@Path("cardId") cardId: Int): Call<Card>
}
