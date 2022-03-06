package com.sayaandreas.baikanandroid.model

data class Testimony(val name: Char, val title: String, val description: String) {
    companion object {
        fun getAll(): List<Testimony> {
            return listOf(
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
                ),
                Testimony(
                    'D',
                    "Jadi bisa ketemu penyelesaian masalahku dan beneran termotivasi untuk ngelakuin solusinya",
                    "Jadi belakangan ini aku menghadapi sebuah masalah yang bener2 buat aku “mentok” dan frustasi karena tiap kali aku minta opini dari teman2 terdekat/pasanganku mereka selalu kasih yang subjektif (memihak ke aku). Sehingga aku nggak pernah bisa ambil keputusan yang aku bisa percaya dan yakin."
                ),
                Testimony(
                    'E',
                    "Bikin aku jadi banyak mikir dan ketemu banyak \"Aha!\" momen nya…",
                    "Jadi ini pertama kali aku konseling online, dan jujur aja aku sama sekali ga tau what to expect. Tapi aku memutuskan untuk coba karna belakangan aku struggle dengan sebuah kondisi yang buat aku engga banget dan ingin untuk nggak gini lagi. Impresi pertamaku dengan Baikan ini cukup baik, karna keliatan profesional dan setelah baca testimoni2nya aku jadi ada gambaran."
                ),
                Testimony(
                    'F',
                    "Seenggaknya ngomong, jangan disimpan sendiri",
                    "Bersyukur setelah saya hubungi admin nya sangat fast respon, dan terlebih saya merasa dinaungi... dari situlah saya memutuskan untuk mencoba konseling dengan mbak Amirra sebagai konselor saya. Saya sangat bersyukur karena di sesi konseling ini saya merasa sangat didengarkan, dan saya bisa cerita panjang lebar sampai lega semuanya... jujur hanya itu sih yang saya cari dari awal, dan saya dapat itu. Rasanya seperti ada beban yang terkurangi dari pundak saya."
                ),
                Testimony(
                    'G',
                    "Bang Edo ngasih hal-hal yang sifatnya praktikal, yang buat saya bisa praktekin dan langsung kerjain trus ngaruh di saya",
                    "Saya sudah konseling sama Bang Edo ini 4x, jadi selama sebulan konseling rutin. Overall sih baik, problem saya ada solusinya. Buat saya konselingnya sangat membantu, nggak ada hal yang gimana gimana juga selama konseling. Saya datang dengan saya tahu kebutuhan saya apa gitu dan saya bersuyukur konselingnya bermanfaat. Saya dapet dorongan dari partner saya buat konseling sama Psikolog, karna menurut dia udah lama ditunda-tunda."
                ),
                Testimony(
                    'H',
                    "Saya mendapatkan jawaban setelah konseling sama konselor Edward",
                    "Bagus yah, dia sangat mau ngedengerin, bertindak sebagai temen jadi bukan sebagai kayak ketemu dokter gitu. Saya nggak ngerasa bayar jasa, jadi seolah-olah ngobrol sama temen. Tapi style orang kan beda-beda ya, saya lebih suka yang semakin informal, semakin mengalir, semakin kayak temen, jadi makin enak ngomongnya. Dia spesialisasinya juga pas sama masalah saya. "
                ),
                Testimony(
                    'I',
                    "beliau bisa ngerti seluk beluk saya, tentang kepribadian saya dan keinginan saya itu seperti apa. Itu bener-bener valid",
                    "Saya pernah ikut konseling tatap muka, tapi ternyata yang saya dapatkan sama sekali tidak memberikan saya kelegaan. Karena tujuan saya ikut konseling kan, setidaknya biar saya ngerti harus seperti apa. Dan saran-saran yang diberikan saat itu, saat konseling tatap muka itu saran2 yang klise kayak banyak-banyak berdoa, banyak2 ibadah, jadi kayak curhat ke orang tua gitu. Tapi untungnya waktu konseling sama konselor Edward, saya diberikan pendapat dan saran2 yang masuk akal, yang benar2 ilmiah dan saya juga heran padahal saya cuma cerita saja tapi beliau bisa ngerti seluk beluk saya, tentang kepribadian saya dan keinginan saya itu seperti apa. Itu bener-bener valid."
                ),
                Testimony(
                    'J',
                    "Saya merasa ada seseorang yang hadir buat saya.",
                    "Secara general, proses konseling yang sudah saya lakukan dengan konselor Sylvia itu sangat membantu. Saya berpikir untuk konseling karena saya tahu saya butuh, dan konselor Sylvia memenuhi ekspektasi saya. Jadi saya merasa tercukupi dengan harapan saya. Prosesnya bener-bener natural, obrolan kami ngalir, saya juga mengutarakan segala pikiran saya itu ngga ada batasan. Saya bener-bener enjoy. Bahkan saat proses konselingpun saya merasa lebih tenang."
                ),
                Testimony(
                    'K',
                    "saya dapet insight tentang bagaimana cara manusia itu merespon terhadap kejadian itu",
                    "Saya merasa sangat terbantu dari konseling bersama Bu Sylvia. Dari saya menyampaikan keluhan saya, lalu dapet insight-insight yang selama ini ngga saya lihat dan ngga saya pikirkan. Terus setelah konseling juga saya coba praktekan yang Bu Sylvia kasih tau dan beberapa hal langsung saya rasain. Cara saya melihat suatu kejadian itu jadi beda. Cara pandang saya jadi beda. Dan saya dapet insight tentang bagaimana cara manusia itu merespon terhadap kejadian itu."
                ),
                Testimony(
                    'L',
                    "Lebih terbuka dalam pemikiran, jadi punya banyak opsi dari pada apa yang saya pikirkan sebelumnya.",
                    "Ini pertama kali konseling sama psikolog, awalnya agak ragu buat cerita tapi pendekatan konselor Sylvia tenang meskipun saya tegang di awal. Jadi terbawa untuk ikut ngobrol dan ngga kaya wawancara. Ada poin penting yang saya dapet setelah konseling sama dia, poin pertama pola pikir. Kemaren problem saya adalah terlalu cemas atau mudah cemas berlebihan."
                ),
                Testimony(
                    'L',
                    "Berkat kakak aku bisa menemukan pendirianku dan bangkit jadi pribadi yang lebih baik",
                    "Kak terimakasih banyak ya sudah membantu aku untuk bisa mengungkapkan semuanya yg ada di diri aku. Alhamdulillah perasaan aku sudah lega bgt semuanya dan tidak menjadi beban lagi... berkat bantuan kak Cindy aku bisa menemukan pendirianku dan bisa bangkit menjadi pribadi yang lebih baik."
                ),
                Testimony(
                    'N',
                    "Jadi sadar bahwa aku ini normal, dan mampu untuk overcome masalahku",
                    "Jadi aku mutusin untuk konseling dengan psikolog ini karna aku punya masalah pengendalian emosi yang udah aku coba ceritain ke teman terdekatku tapi dapat respon yang nggak memuaskan. Ada yang judgmental, ada yg ga peduli sama sekali, atau ada yang kasih saran bagus tapi cara penyampaiannya ga enak sama sekali. Akhirnya aku browsing dan ketemu Baikan di nomor 1. Langsung sreg karna aku langsung bisa liat konselor dan profil lengkap nya, dan ketika chat sama adminnya aku langsung dipertemukan dengan konselor yang memang dari awal menurutku cocok buatku, yaitu Cindy Anjani Eridani."
                ),
                Testimony(
                    'O',
                    "Konselornya open-minded dan nggak kolot meskipun topikku agak \"tabu\"",
                    "Aku memutuskan untuk butuh konseling karena belakangan ada masalah yang sebetulnya sudah lama aku rasakan, tapi belakangan ini berasa banget jadi beban pikiran dan sangat mengganggu. Akhirnya memutuskan untuk konseling dan karena situasi pandemik COVID19 ini nggak memungkinkan untuk keluar, aku jadi cari alternatif dengan konseling online dan ketemulah dengan Baikan dan dicocokkan dengan kak Cindy sebagai konselorku."
                ),
                Testimony(
                    'P',
                    "Begitu aku face my feelings head on, malah makin cepat terasa enteng",
                    "Yang sangat aku appreciate dari kak Mario adalah niatnya untuk bener2 ngebantu aku dalam masalah yang aku alami. Karena jujur ini adalah pertama kalinya aku konseling, setelah berulang kali berusaha untuk fix masalah personalku sendiri tapi selalu gagal. Akhirnya aku tertarik untuk cari opini dari seseorang yang expert di bidang psikologi. Yang aku pelajarin banget setelah konseling dengan kak Mario adalah: It’s important to face my own feelings."
                ),
                Testimony(
                    'Q',
                    "Jadi bisa mendapat sisi rasionalitas yang kita butuhkan tentang permasalahan kita dari seorang profesional",
                    "Keinginanku konseling itu berawal dari diceritain temen tentang Baikan, dan sebagai mahasiswi psikologi, aku juga tertarik untuk tau nih gimana rasanya konseling, dan mengenal diri sendiri lebih dalam. Aku dipertemukan dengan kak Mario sebagai konselorku, dan bersyukur konselingnya berjalan dengan lancar. No problem at all. Hal terpenting yang aku dapet adalah keyakinan bahwa aku nggak kenapa2, semua gejolak emosi/mood yang aku alami itu masih dalam ambang batas yang normal dan wajar."
                ),
                Testimony(
                    'R',
                    "Bisa cerita tentang hal2 yang nggak pernah aku ceritain sebelumnya dengan dalam, tanpa di judge sama sekali",
                    "Jadi sebenarnya aku itu orangnya cukup terbuka ya, aku yakin juga orang2 ngeliat aku begitu. Tapi sejujurnya, selama ini yang aku terbuka di depan orang itu cuma “kulit” nya aja, ngga pernah dalem. Padahal mah sebenernya aku orangnya overthinking banget... sampai2 aku merasa tendensi untuk overthinking ini sangat amat mengganggu hidupku (sempet nangis2 gajelas). Dan setelah aku denger bahwa temenku udah coba konseling di Baikan dan terbantu banget, aku jadi berani untuk akhirnya memutuskan untuk cari bantuan profesional... sebelumnya aku maju-mundur timbang2 mau ke psikolog selama setahun terakhir."
                )
            )
        }
    }
}
