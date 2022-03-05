package com.sayaandreas.baikanandroid.model

import androidx.annotation.DrawableRes
import java.util.*

data class Counselor(
    @DrawableRes val image: Int,
    val name: String,
    val motto: String,
    val description: String,
    val specialist: List<Specialist>,
    val schedule: List<Date>,
    val testimonies: List<Testimony>,
    val patients: Int
)
