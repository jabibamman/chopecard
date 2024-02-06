package com.chopecard.domain.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductStore(
    val id: Int,
    val product: Product,
    val quantity: Int,
    val price: Float
) : Parcelable
