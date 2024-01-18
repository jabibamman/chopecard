package com.chopecard.data.network

import com.chopecard.data.model.CardInfoResponse
import com.chopecard.data.model.CardSetInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query
interface CardApiService {
    @GET("cardinfo.php")
    suspend fun getCardInfo(@Query("name") cardName: String): CardInfoResponse

    @GET("cardinfo.php")
    suspend fun getCardsInfoByType(@Query("type") cardType: String): CardInfoResponse

    @GET("cardsetsinfo.php")
    suspend fun getCardSetInfo(@Query("setcode") setCode: String): CardSetInfoResponse
}