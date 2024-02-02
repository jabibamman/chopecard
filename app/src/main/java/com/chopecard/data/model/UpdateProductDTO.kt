package com.chopecard.data.model

data class UpdateProductDTO(
    val storeId: Int,
    val productId: Int,
    val productStoreDTO: ProductStoreDTO
)
