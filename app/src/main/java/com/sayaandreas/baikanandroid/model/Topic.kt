package com.sayaandreas.baikanandroid.model

import com.sayaandreas.baikanandroid.R

data class Topic(val title: String, val icon: Int) {
    companion object {
        fun getAll(): List<Topic> {
            return listOf(
                Topic(
                    title = "Pekerjaan",
                    icon = R.drawable.briefcase
                ),
                Topic(
                    title = "Keluarga",
                    icon = R.drawable.family
                ),
                Topic(
                    title = "Pasangan",
                    icon = R.drawable.heart
                ),
                Topic(
                    title = "Emosi",
                    icon = R.drawable.poker_face
                ),
                Topic(
                    title = "Pendidikan",
                    icon = R.drawable.mortarboard
                ),
                Topic(
                    title = "Kesepian",
                    icon = R.drawable.alone
                ),
                Topic(
                    title = "Sosial",
                    icon = R.drawable.social_media
                ),
                Topic(
                    title = "Kecanduan",
                    icon = R.drawable.brain
                ),
                Topic(
                    title = "Lainnya",
                    icon = R.drawable.more
                )
            )
        }
    }
}
