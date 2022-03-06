package com.sayaandreas.baikanandroid.model

import androidx.annotation.DrawableRes

data class ImageDataLocal(val descciption: String, @DrawableRes val resId: Int)
data class ImageDataRemote(val descciption: String, val src: String)
