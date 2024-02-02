package com.chopecard.domain.models

import java.io.Serializable

data class Store(
    val id: Int,
    val name: String,
    val address: String,
    val products: List<ProductStore>
) : Serializable