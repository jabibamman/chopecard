package com.chopecard.data.model

data class UserDTO(val name: String, val email: String, val password: String, val role:String = "USER")
