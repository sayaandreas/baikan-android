package com.sayaandreas.baikanandroid.model

data class User(val name: String, val email: String)

data class RegisterUser(
    val name: String = "",
    val email: String = "",
    val gender: String = "",
    val phone: String = "",
    val BirthDate: String = "",
    val address: String = ""
)