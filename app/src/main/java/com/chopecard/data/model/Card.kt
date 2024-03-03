@file:Suppress("PropertyName", "PropertyName", "PropertyName", "PropertyName", "PropertyName",
    "PropertyName", "PropertyName", "PropertyName"
)

package com.chopecard.data.model

data class CardResponse(val data: List<Card>)

@Suppress("PropertyName", "PropertyName", "PropertyName", "PropertyName", "PropertyName")
data class Card(
    val id: Long,
    val name: String,
    val type: String,
    val frameType: String,
    val desc: String,
    val atk: Int,
    val def: Int,
    val level: Int,
    val race: String,
    val attribute: String,
    val card_sets: List<CardSet>,
    val card_images: List<CardImage>,
    val card_prices: List<CardPrice>
)

data class CardSet(
    val set_name: String,
    val set_code: String,
    val set_rarity: String,
    val set_rarity_code: String,
    val set_price: String
)

data class CardImage(
    val id: Long,
    val image_url: String,
    val image_url_small: String,
    val image_url_cropped: String
)

data class CardPrice(
    val cardmarket_price: String,
    val tcgplayer_price: String,
    val ebay_price: String,
    val amazon_price: String,
    val coolstuffinc_price: String
)


data class CardInfoResponse(val data: List<Card>)
data class CardSetInfoResponse(val data: List<CardSet>)
