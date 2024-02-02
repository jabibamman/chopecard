package com.chopecard.domain.models

data class ProductStore(
    val id: Int,
    val product: Product,
    val quantity: Int,
    val price: Float
)