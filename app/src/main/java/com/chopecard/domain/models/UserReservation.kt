package com.chopecard.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserReservation(val store: Store, val productStore: ProductStore, val quantity: Int, val id: Int) : Parcelable
