package com.sayaandreas.baikanandroid.model

import androidx.annotation.DrawableRes
import com.sayaandreas.baikanandroid.R
import java.util.*

data class Counselor(
    val image: String,
    val name: String,
    val fullName: String,
    val firstName: String,
    val motto: String,
    val description: String,
    val specialist: List<Topic>,
    val schedule: List<Date>,
    val testimonies: List<Testimony>,
    val patients: Int
) {
    companion object {
        fun getAll(): List<Counselor> {
            val testimonies = Testimony.getAll()
            val topics = Topic.getAll()
            return listOf(
                Counselor(
                    image = "https://bicarakan-prod-bucket.s3.amazonaws.com/media/uploads/counselors/psikolog_bicarakan.id_ray_caesarly.webp",
                    name = "Ray Caesarly",
                    fullName = "Ray Caesarly, M. Psi., Psikolog",
                    firstName = "Ray",
                    motto = "Let’s be more kind toward others and ourselves",
                    description = "Ray adalah seorang psikolog klinis yang memiliki ketertarikan pada pengembangan diri individu melalui praktik-praktik compassion (belas kasih). Ray percaya bahwa setiap manusia berhak bahagia dan terbebas dari penderitaan dengan memberdayakan seluruh kapasitas yang dimiliki individu secara optimal sehingga menjadi versi terbaik dari diri mereka. Ray menangani kasus-kasus ketidakpercayaan diri, gangguan kecemasan, dan gangguan emosi serta fungsi diri.",
                    specialist = topics.subList(0, 3),
                    schedule = listOf(
                        Date(2022, 2, 22, 9, 0),
                        Date(2022, 2, 23, 10, 0)
                    ),
                    testimonies = testimonies.subList(0, 3), patients = 102
                ),
                Counselor(
                    image = "https://bicarakan-prod-bucket.s3.amazonaws.com/media/uploads/counselors/psikolog-jakarta-amirra.webp",
                    name = "Amirra Nur'indah",
                    fullName = "Amirra Nur'indah, M. Psi., Psikolog",
                    firstName = "Amirra",
                    motto = "It’s only after you’ve stepped outside your comfort zone that you begin to change, grow, and transform.",
                    description = "Amirra adalah seorang psikolog anak dan dewasa yang berpengalaman di bidang Relationship Counseling (Pernikahan), Trauma dari Abuse/Kekerasan (baik itu fisik maupun mental), dan Pengembangan Diri/Self-Development. Sebagai seorang psikolog yang terdaftar dalam keanggotaan IATC (Indonesian Art Therapy Community), Amirra selalu berupaya menciptakan hubungan therapeutic dengan klien dengan tujuan membantu mereka mencapai kehidupan yang seimbang, berkualitas, dan bermakna. Diluar pekerjaannya sebagai seorang psikolog, dia senang memasak, travelling, dan mendengarkan dengan simpati berbagai cerita hidup dari orang lain.",
                    specialist = topics.subList(3, 6),
                    schedule = listOf(
                        Date(2022, 2, 22, 13, 0),
                        Date(2022, 2, 23, 16, 0)
                    ),
                    testimonies = testimonies.subList(3, 6), patients = 93
                ),
                Counselor(
                    image = "https://bicarakan-prod-bucket.s3.amazonaws.com/media/uploads/counselors/psikolog-jakarta-edward.webp",
                    name = "Edward Gani",
                    fullName = "Edward Gani, M. Psi., Psikolog",
                    firstName = "Edward",
                    motto = "You are not defined by your problems, but rather by your responses.",
                    description = "Edward Septianto merupakan psikolog klinis dari Universitas Tarumanegara yang memiliki ketertarikan kuat pada pengembangan diri individu. Edward meyakini bahwa setiap individu mampu untuk berdaya dengan cara mereka masing-masing. Peran psikolog disini adalah sebagai fasilitator yang dapat membantumu menemukan & membentuk versi terbaik dari dirimu. Versi terbaik ini tentunya dapat dilihat ketika seseorang dapat berfungsi pada aspek pekerjaan, relasi, dan hobinya. Edward memiliki minat yang dalam pada konflik relasi antar pasangan atau keluarga, permasalahan emosi, kecemasan, depresi, isu seksualitas, dan gangguan psikologis lainnya yang dapat mempengaruhi fungsi individu.",
                    specialist = topics.subList(6, 9),
                    schedule = listOf(
                        Date(2022, 2, 20, 9, 0),
                        Date(2022, 2, 21, 10, 0)
                    ),
                    testimonies = testimonies.subList(6, 9), patients = 65
                ),
                Counselor(
                    image = "https://bicarakan-prod-bucket.s3.amazonaws.com/media/uploads/counselors/psikolog-sylvia-angelika-dari-bicarakan-id.jpg",
                    name = "Sylvia Angelika",
                    fullName = "Sylvia Angelika, M. Psi., Psikolog",
                    firstName = "Sylvia",
                    motto = "Kamu berharga dan kamu pantas untuk didengar",
                    description = "Sylvia Angelika, M. Psi., Psikolog merupakan Psikolog Klinis lulusan dari Universitas Tarumanagara. Sylvia memiliki ketertarikan dalam bidang emosi, kecemasan, depresi, serta stres. Sylvia juga memiliki pengalaman menangani kasus yang berkaitan dengan masalah dalam hubungan romantis, masalah dalam support system, pemilihan karier, ataupun stres kehidupan sehari-hari. Sylvia meyakini bahwa sikap penerimaan dan keterbukaan menjadi kunci dalam proses konseling yang dilakukan. Sylvia akan berkolaborasi denganmu untuk menghadapi permasalahan yang dialami, sehingga kualitas hidupmu dapat meningkat.",
                    specialist = listOf(topics[1], topics[3], topics[5]),
                    schedule = listOf(
                        Date(2022, 2, 20, 9, 0),
                        Date(2022, 2, 21, 10, 0)
                    ),
                    testimonies = testimonies.subList(9, 12), patients = 131
                ),
                Counselor(
                    image = "https://bicarakan-prod-bucket.s3.amazonaws.com/media/uploads/counselors/psikolog-jakarta-cindy_IKNVvfw.png",
                    name = "Cindy Anjani Eridani",
                    fullName = "Cindy Anjani Eridani, S.Psi.",
                    firstName = "Cindy",
                    motto = "Believe in yourself. The biggest strength comes from within. We just have to find it.",
                    description = "Meraih gelar sarjananya di Universitas Gunadarma, Cindy Anjani Eridani adalah seorang konselor profesional yang berpengalaman di bidang Stress and Relaxation, Special Needs Children, Addiction (berpengalaman di Badan Narkotika Nasional), Sexual Education, Stress & Anxiety Management, dan Self-Improvement. Saat ini, Cindy tengah menjalani studi S2 nya untuk mendapatkan Master's Degree di bidang psikologi klinis. Sebagai seorang konselor Bicarakan.id, Cindy punya misi untuk membantu kamu menjadi versi yang terbaik dari dirimu — sehingga kamu bisa menjalani hidup yang terbaik untukmu dan semua orang yang kamu cintai. Dikarenakan keterbatasan dari universitas konselor Cindy, ada kendala kelulusan secara resmi yang diakibatkan oleh pandemi, dimana konselor Cindy sudah bisa lulus dari tahun lalu, namun dikarenakan hal ini jadi kelulusannya jadi tertunda.",
                    specialist = listOf(topics[6], topics[3], topics[8]),
                    schedule = listOf(
                        Date(2022, 2, 20, 9, 0),
                        Date(2022, 2, 21, 10, 0)
                    ),
                    testimonies = testimonies.subList(12, 15), patients = 84
                ),
                Counselor(
                    image = "https://bicarakan-prod-bucket.s3.amazonaws.com/media/uploads/counselors/psikolog-mario-albert-dari-bicarakan-id.jpg",
                    name = "Mario Albert",
                    fullName = "Mario Albert, M. Psi., Psikolog",
                    firstName = "Mario",
                    motto = "Improve the future towards a better, bigger, more mature you",
                    description = "Memperoleh kualifikasi sebagai Psikolog Klinis dari Universitas Tarumanagara, Mario Albert percaya bahwa “If you don’t say what you think, you’re punishing yourself.” Melakukan percakapan secara terbuka merupakan proses yang penting dalam sesi konseling. Mario memiliki pengalaman dalam menangani masalah pada toxic relationship, depresi, kecemasan, pengendalian emosi, dan pencarian makna hidup. Sebagai konselor Bicarakan.id, Mario memiliki misi untuk membantu klien untuk meningkatkan kualitas hidupnya sehingga bisa lebih bermakna.",
                    specialist = listOf(topics[8], topics[4], topics[5]),
                    schedule = listOf(
                        Date(2022, 2, 20, 9, 0),
                        Date(2022, 2, 21, 10, 0)
                    ),
                    testimonies = testimonies.subList(15, 18), patients = 44
                )
            )
        }
    }
}