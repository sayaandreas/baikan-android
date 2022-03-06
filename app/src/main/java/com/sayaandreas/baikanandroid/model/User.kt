package com.sayaandreas.baikanandroid.model

import java.time.LocalDate

data class User(
    val name: String,
    val email: String,
    val gender: String = "",
    val phone: String = "",
    val birthDate: LocalDate = LocalDate.parse("1980-06-02"),
    val address: String = ""
) {
    companion object {
        fun getAll(): List<User> {
            return listOf(
                User(
                    name = "Johny Pramono",
                    email = "johny@company.com",
                    gender = "Laki-laki",
                    phone = "081234567890",
                    birthDate = LocalDate.parse("1995-06-02"),
                    address = "Jakarta"
                ),
                User(
                    name = "Johny Pramono",
                    email = "johnypersonal@gmail.com",
                    gender = "Laki-laki",
                    phone = "081234567890",
                    birthDate = LocalDate.parse("1995-06-02"),
                    address = "Jakarta"
                ),
                User(
                    name = "Cesar Azpilicueta",
                    email = "cesar@gmail.com",
                    gender = "Laki-laki",
                    phone = "081287654321",
                    birthDate = LocalDate.parse("1997-06-02"),
                    address = "Jakarta"
                ),
                User(
                    name = "Maria Sharapova",
                    email = "maria@gmail.com",
                    gender = "Perempuan",
                    phone = "081200000",
                    birthDate = LocalDate.parse("1993-06-02"),
                    address = "Bandung"
                ),
            )
        }
    }
}