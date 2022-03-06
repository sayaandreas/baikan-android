package com.sayaandreas.baikanandroid.model

import androidx.annotation.DrawableRes
import androidx.compose.material.MaterialTheme
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.ui.theme.Green500
import com.sayaandreas.baikanandroid.ui.theme.Pink500

sealed class PackageType(val name: String, @DrawableRes val resId: Int) {
    object Text : PackageType(name = "Text", resId = R.drawable.ic_phone_call_media)
    object VoiceCall : PackageType(name = "Call", resId = R.drawable.ic_phone_call_media)
    object VideoCall : PackageType(name = "Vidcall", resId = R.drawable.ic_video_call_media)
}

data class Package(
    val id: Int,
    val name: String,
    val sessionQty: Int,
    val sessionPrice: String,
    val baseTotalPrice: String,
    val finalTotalPrice: String,
    val description1: String,
    val description2: String,
    val type: PackageType,
    val tag: Tag?,
) {
    companion object {
        fun getAll(): List<Package> {
            return listOf(
                Package(
                    id = 1,
                    name = "Paket Perkenalan",
                    sessionQty = 1,
                    sessionPrice = "100.000",
                    baseTotalPrice = "100.000",
                    finalTotalPrice = "100.000",
                    description1 = "Harga 100.000/sesi",
                    description2 = "Berlaku 1 minggu",
                    type = PackageType.Text,
                    tag = Tag(text = "For First Timer", color = Pink500)
                ),
                Package(
                    id = 2,
                    name = "Paket Lega",
                    sessionQty = 4,
                    sessionPrice = "100.000",
                    baseTotalPrice = "400.000",
                    finalTotalPrice = "350.00",
                    description1 = "Harga 87.500/sesi, kamu hemat 12.500",
                    description2 = "Berlaku 1 bulan",
                    type = PackageType.Text,
                    tag = null
                ),
                Package(
                    id = 3,
                    name = "Paket Nyaman",
                    sessionQty = 8,
                    sessionPrice = "100.000",
                    baseTotalPrice = "800.000",
                    finalTotalPrice = "640.000",
                    description1 = "Harga 80.000/sesi, kamu hemat 20.000",
                    description2 = "Berlaku 2 bulan",
                    type = PackageType.Text,
                    tag = Tag(text = "Best Value", color = Green500)
                ),
                Package(
                    id = 4,
                    name = "Paket Perkenalan",
                    sessionQty = 1,
                    sessionPrice = "150.000",
                    baseTotalPrice = "150.000",
                    finalTotalPrice = "150.000",
                    description1 = "Harga 150.000/sesi",
                    description2 = "Berlaku 1 minggu",
                    type = PackageType.VoiceCall,
                    tag = Tag(text = "For First Timer", color = Pink500)
                ),
                Package(
                    id = 5,
                    name = "Paket Lega",
                    sessionQty = 4,
                    sessionPrice = "150.000",
                    baseTotalPrice = "600.000",
                    finalTotalPrice = "550.000",
                    description1 = "Harga 137.500/sesi, kamu hemat 11.500",
                    description2 = "Berlaku 1 bulan",
                    type = PackageType.VoiceCall,
                    tag = Tag(text = "Best Value", color = Green500)
                ),
                Package(
                    id = 6,
                    name = "Paket Perkenalan",
                    sessionQty = 1,
                    sessionPrice = "175.000",
                    baseTotalPrice = "175.000",
                    finalTotalPrice = "175.00",
                    description1 = "Harga 175.000/sesi",
                    description2 = "Berlaku 1 minggu",
                    type = PackageType.VideoCall,
                    tag = Tag(text = "For First Timer", color = Pink500)
                ),
            )
        }
    }
}