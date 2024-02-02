package com.chopecard.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Store(
    val id: Int,
    val name: String,
    val address: String,
    val products: List<ProductStore>
) : Parcelable