package com.chopecard.domain.models

data class UserReservation(val store: Store, val productStore: ProductStore, val quantity: Int, val id: Int)
