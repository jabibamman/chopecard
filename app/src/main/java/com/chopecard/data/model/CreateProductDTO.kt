package com.chopecard.data.model

data class CreateProductDTO(
    val storeId: Int,
    val productId: Int,
    val productStoreDTO: ProductStoreDTO
)
