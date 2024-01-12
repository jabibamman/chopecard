package com.chopecard.domain.models

data class Users(val userId: Int, val name: String, val email: String, val reservations: List<UserReservation>)
