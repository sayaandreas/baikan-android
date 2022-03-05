package com.sayaandreas.baikanandroid.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.model.Counselor
import com.sayaandreas.baikanandroid.model.Specialist
import com.sayaandreas.baikanandroid.model.Testimony
import com.sayaandreas.baikanandroid.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel : ViewModel() {
    val counselorList = listOf(
        Counselor(
            image = R.drawable.counselor_ray_caesarly,
            name = "Ray Caesarly, M. Psi., Psikolog",
            motto = "Let’s be more kind toward others and ourselves",
            description = "Ray adalah seorang psikolog klinis yang memiliki ketertarikan pada pengembangan diri individu melalui praktik-praktik compassion (belas kasih). Ray percaya bahwa setiap manusia berhak bahagia dan terbebas dari penderitaan dengan memberdayakan seluruh kapasitas yang dimiliki individu secara optimal sehingga menjadi versi terbaik dari diri mereka. Ray menangani kasus-kasus ketidakpercayaan diri, gangguan kecemasan, dan gangguan emosi serta fungsi diri.",
            specialist = listOf(
                Specialist.SelfCriticism,
                Specialist.Psikosomatis,
                Specialist.GangguanKecemasan
            ),
            schedule = listOf(
                Date(2022, 2, 22, 9, 0),
                Date(2022, 2, 23, 10, 0)
            ),
            testimonies = listOf(
                Testimony(
                    'A',
                    "aku lebih bisa mengenal diriku secara lebih jelas",
                    "Ini pertama kali saya mencoba untuk menggunakan jasa konselor ya karena dari dulu gak pernah terfikir dan terfikir pun nyarinya susah dan nemunya di website Baikan dan karena saya melihat konselor-konselornya terpampang di situ buat saya lebih nyaman"
                ),
                Testimony(
                    'B',
                    "aku sekarang jadi mulai mau berubah",
                    "Saya konseling kemaren tanggal 28 Agustus ya hari Sabtu. Much better yang saya rasain sekarang soalnya dari Kak Raynya juga dia good listener sih, jadi dari awalnya dia dengerin keluhan saya apa aja trus sabar dengerinnya."
                ),
                Testimony(
                    'C',
                    "How to process the emotion",
                    "Sebenernya ok sih, lancar maksudnya dia bisa menjelaskan dulu mengarahkan ya ada apa gitu kalo kebetulan saya juga kadang-kadang suka susah bercerita karena bingung harus mulai dari mana, biasalah itulah ya."
                )
            ), patients = 102
        ),
        Counselor(
            image = R.drawable.counselor_amirra,
            name = "Amirra Nur'indah, M. Psi., Psikolog",
            motto = "It’s only after you’ve stepped outside your comfort zone that you begin to change, grow, and transform.",
            description = "Amirra adalah seorang psikolog anak dan dewasa yang berpengalaman di bidang Relationship Counseling (Pernikahan), Trauma dari Abuse/Kekerasan (baik itu fisik maupun mental), dan Pengembangan Diri/Self-Development. Sebagai seorang psikolog yang terdaftar dalam keanggotaan IATC (Indonesian Art Therapy Community), Amirra selalu berupaya menciptakan hubungan therapeutic dengan klien dengan tujuan membantu mereka mencapai kehidupan yang seimbang, berkualitas, dan bermakna. Diluar pekerjaannya sebagai seorang psikolog, dia senang memasak, travelling, dan mendengarkan dengan simpati berbagai cerita hidup dari orang lain.",
            specialist = listOf(
                Specialist.HubunganInterpersonal,
                Specialist.Berduka,
                Specialist.Seksualitas
            ),
            schedule = listOf(
                Date(2022, 2, 22, 13, 0),
                Date(2022, 2, 23, 16, 0)
            ),
            testimonies = listOf(
                Testimony(
                    'A',
                    "Jadi bisa ketemu penyelesaian masalahku dan beneran termotivasi untuk ngelakuin solusinya",
                    "Jadi belakangan ini aku menghadapi sebuah masalah yang bener2 buat aku “mentok” dan frustasi karena tiap kali aku minta opini dari teman2 terdekat/pasanganku mereka selalu kasih yang subjektif (memihak ke aku). Sehingga aku nggak pernah bisa ambil keputusan yang aku bisa percaya dan yakin."
                ),
                Testimony(
                    'B',
                    "Bikin aku jadi banyak mikir dan ketemu banyak \"Aha!\" momen nya…",
                    "Jadi ini pertama kali aku konseling online, dan jujur aja aku sama sekali ga tau what to expect. Tapi aku memutuskan untuk coba karna belakangan aku struggle dengan sebuah kondisi yang buat aku engga banget dan ingin untuk nggak gini lagi. Impresi pertamaku dengan Baikan ini cukup baik, karna keliatan profesional dan setelah baca testimoni2nya aku jadi ada gambaran."
                ),
                Testimony(
                    'C',
                    "Seenggaknya ngomong, jangan disimpan sendiri",
                    "Bersyukur setelah saya hubungi admin nya sangat fast respon, dan terlebih saya merasa dinaungi... dari situlah saya memutuskan untuk mencoba konseling dengan mbak Amirra sebagai konselor saya. Saya sangat bersyukur karena di sesi konseling ini saya merasa sangat didengarkan, dan saya bisa cerita panjang lebar sampai lega semuanya... jujur hanya itu sih yang saya cari dari awal, dan saya dapat itu. Rasanya seperti ada beban yang terkurangi dari pundak saya."
                )
            ), patients = 93
        ),
        Counselor(
            image = R.drawable.counselor_edward,
            name = "Edward Gani, M. Psi., Psikolog",
            motto = "You are not defined by your problems, but rather by your responses.",
            description = "Edward Septianto merupakan psikolog klinis dari Universitas Tarumanegara yang memiliki ketertarikan kuat pada pengembangan diri individu. Edward meyakini bahwa setiap individu mampu untuk berdaya dengan cara mereka masing-masing. Peran psikolog disini adalah sebagai fasilitator yang dapat membantumu menemukan & membentuk versi terbaik dari dirimu. Versi terbaik ini tentunya dapat dilihat ketika seseorang dapat berfungsi pada aspek pekerjaan, relasi, dan hobinya. Edward memiliki minat yang dalam pada konflik relasi antar pasangan atau keluarga, permasalahan emosi, kecemasan, depresi, isu seksualitas, dan gangguan psikologis lainnya yang dapat mempengaruhi fungsi individu.",
            specialist = listOf(
                Specialist.KarirPekerjaan,
                Specialist.StressDepresi,
                Specialist.Overthinking
            ),
            schedule = listOf(
                Date(2022, 2, 20, 9, 0),
                Date(2022, 2, 21, 10, 0)
            ),
            testimonies = listOf(
                Testimony(
                    'A',
                    "Bang Edo ngasih hal-hal yang sifatnya praktikal, yang buat saya bisa praktekin dan langsung kerjain trus ngaruh di saya",
                    "Saya sudah konseling sama Bang Edo ini 4x, jadi selama sebulan konseling rutin. Overall sih baik, problem saya ada solusinya. Buat saya konselingnya sangat membantu, nggak ada hal yang gimana gimana juga selama konseling. Saya datang dengan saya tahu kebutuhan saya apa gitu dan saya bersuyukur konselingnya bermanfaat. Saya dapet dorongan dari partner saya buat konseling sama Psikolog, karna menurut dia udah lama ditunda-tunda."
                ),
                Testimony(
                    'B',
                    "Saya mendapatkan jawaban setelah konseling sama konselor Edward",
                    "Bagus yah, dia sangat mau ngedengerin, bertindak sebagai temen jadi bukan sebagai kayak ketemu dokter gitu. Saya nggak ngerasa bayar jasa, jadi seolah-olah ngobrol sama temen. Tapi style orang kan beda-beda ya, saya lebih suka yang semakin informal, semakin mengalir, semakin kayak temen, jadi makin enak ngomongnya. Dia spesialisasinya juga pas sama masalah saya. "
                ),
                Testimony(
                    'C',
                    "beliau bisa ngerti seluk beluk saya, tentang kepribadian saya dan keinginan saya itu seperti apa. Itu bener-bener valid",
                    "Saya pernah ikut konseling tatap muka, tapi ternyata yang saya dapatkan sama sekali tidak memberikan saya kelegaan. Karena tujuan saya ikut konseling kan, setidaknya biar saya ngerti harus seperti apa. Dan saran-saran yang diberikan saat itu, saat konseling tatap muka itu saran2 yang klise kayak banyak-banyak berdoa, banyak2 ibadah, jadi kayak curhat ke orang tua gitu. Tapi untungnya waktu konseling sama konselor Edward, saya diberikan pendapat dan saran2 yang masuk akal, yang benar2 ilmiah dan saya juga heran padahal saya cuma cerita saja tapi beliau bisa ngerti seluk beluk saya, tentang kepribadian saya dan keinginan saya itu seperti apa. Itu bener-bener valid."
                )
            ), patients = 65
        )
    )
    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    private val _user: MutableState<User?> = mutableStateOf(null)
    val user: State<User?> get() = _user

    private val _selectedTopic: MutableState<String> = mutableStateOf("")
    val selectedTopic: State<String> get() = _selectedTopic

    init {
        viewModelScope.launch {
            delay(2000)
            doneLoading()
        }
    }

    private fun doneLoading() {
        _isLoading.value = false
    }

    fun selectTopic(topic: String) {
        _selectedTopic.value = topic
    }

    fun setCurrentUser(user: User) {
        _user.value = user
    }
}