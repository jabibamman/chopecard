package com.chopecard.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val priceMin: Float,
    val priceMax: Float
) : Parcelable
