package com.chopecard.domain.models

data class User(val userId: Int, val name: String, val email: String, val reservations: List<UserReservation>, val role:String)
