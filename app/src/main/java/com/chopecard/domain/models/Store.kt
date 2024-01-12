package com.chopecard.domain.models

data class Store(val id: Int, val name: String, val address: String, val products: List<ProductStore>)
