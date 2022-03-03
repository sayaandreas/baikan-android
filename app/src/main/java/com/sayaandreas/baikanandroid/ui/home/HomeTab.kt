package com.sayaandreas.baikanandroid.ui.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import com.sayaandreas.baikanandroid.ui.theme.topAppBarLarge
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

data class Counselor(
    val name: String,
    val title: String,
    val specialist: List<String>,
    val avatar: Int
)

data class CounselingStep(val title: String, val description: String)

val counselorList = listOf(
    Counselor(
        name = "Joshua Simorangkir",
        title = "Psikolog - Medan",
        specialist = listOf("Pekerjaan", "Pendidikan", "Sosial"),
        avatar = R.drawable.counselor1

    ),
    Counselor(
        name = "Evan Tanuwijaya",
        title = "Psikolog - Jakarta",
        specialist = listOf("Keluarga", "Pasangan"),
        avatar = R.drawable.counselor2
    ),
    Counselor(
        name = "Anastasya Febriana",
        title = "Psikolog - Bandung",
        specialist = listOf("Emosi", "Kecanduan"),
        avatar = R.drawable.counselor3
    )

)


@Composable
fun HomeScreen(goToCounseling: () -> Unit, toggleDrawer: () -> Job) {
    val scrollState = rememberScrollState(0)
    val (showDialog, setShowDialog) = remember {
        mutableStateOf(false)
    }
    val (showBoosterDialog, setShowBoosterDialog) = remember {
        mutableStateOf<BoosterDialogType?>(null)
    }

    Column() {
        TopAppBar(
            modifier = Modifier
                .zIndex(10f)
                .height(160.dp)
                .clip(MaterialTheme.shapes.topAppBarLarge),

            content = {
                Column(Modifier.fillMaxWidth()) {
                    Row {
                        IconButton(onClick = { toggleDrawer() }) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    }
                    Column(Modifier.padding(start = 14.dp, end = 14.dp, top = 24.dp)) {
                        Text(
                            text = "Halo, Johny Pramono",
                            style = MaterialTheme.typography.h5
                        )
                        Text(text = "You will be fine, even finer")
                    }
                }
            },
            contentColor = MaterialTheme.colors.onPrimary,
            elevation = 8.dp,
        )
        Column(
            Modifier
                .verticalScroll(state = scrollState)
                .fillMaxSize()
                .padding(
                    start = 18.dp,
                    end = 16.dp,
                    bottom = 24.dp,
                    top = 24.dp
                )
        ) {
            MainMenu(showMeditation = {
                setShowDialog(true)
            }, goToCounseling = goToCounseling)
            BoosterMenu(setShowBoosterDialog)
            PromoBanner()
            CounselorList(counselorList)
            Stories()
            CounselingStep()
            Image(
                painter = painterResource(id = R.drawable.hermina),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Podcast()

            if (showDialog) {
                SelfHealingDialog(
                    onDismissRequest = { setShowDialog(false) },
                )
            }

            if (showBoosterDialog != null) {
                BoosterDialog(
                    onDismissRequest = { setShowBoosterDialog(null) },
                    showBoosterDialog
                )
            }
        }
    }
}

@Composable
fun MainMenu(showMeditation: () -> Unit, goToCounseling: () -> Unit) {
    val imgKonseling: Painter = painterResource(id = R.drawable.conversation)
    val imgMeditasi: Painter = painterResource(id = R.drawable.meditation)

    Text(text = "Butuh bantuan apa?", style = MaterialTheme.typography.subtitle1)
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 32.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .size(140.dp)
                .padding(end = 12.dp)
                .clickable {
                    goToCounseling()
                }, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = imgKonseling, contentDescription = "", Modifier.fillMaxWidth(.8f))
            Text(text = "Konseling")
        }
        Column(
            Modifier
                .size(140.dp)
                .padding(start = 12.dp)
                .clickable {
                    showMeditation()
                }, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = imgMeditasi, contentDescription = "", Modifier.fillMaxWidth(.8f))
            Text(text = "Self Healing")
        }
    }
}

@Composable
fun BoosterMenu(setShowDialog: (type: BoosterDialogType) -> Unit) {
    val imgSport: Painter = painterResource(id = R.drawable.sport)
    val imgEntertaiment: Painter = painterResource(id = R.drawable.ic_entertaiment)
    val imgFnb: Painter = painterResource(id = R.drawable.ic_fnb)

    Text(text = "Ingin mengurangi stres mu?", style = MaterialTheme.typography.subtitle1)
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Column(
            Modifier
                .weight(1f)
                .clickable {
                    setShowDialog(BoosterDialogType.Sport)
                }, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = imgSport,
                contentDescription = "",
                Modifier
                    .size(110.dp)
                    .padding(bottom = 8.dp)
            )
            Text(text = "Sport")
        }
        Column(
            Modifier
                .weight(1f)
                .clickable {
                    setShowDialog(BoosterDialogType.Entertaiment)
                }, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = imgEntertaiment,
                contentDescription = "",
                Modifier
                    .size(110.dp)
                    .padding(bottom = 8.dp)
            )
            Text(text = "Entertaiment")
        }
        Column(
            Modifier
                .weight(1f)
                .clickable {
                    setShowDialog(BoosterDialogType.Fnb)
                }, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = imgFnb,
                contentDescription = "",
                Modifier
                    .size(110.dp)
                    .padding(bottom = 8.dp)
            )
            Text(text = "F&B")
        }
    }
}

@Composable
fun PromoBanner() {
    Column(
        Modifier
            .padding(top = 32.dp)
            .clip(MaterialTheme.shapes.large)
            .background(color = MaterialTheme.colors.primaryVariant)
            .padding(24.dp)
    ) {
        Text(text = "Paket Konseling")
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Dengan paket konseling ini kamu mendapatkan sesi konseling dengan total harga yang lebih murah lho",
                modifier = Modifier
                    .weight(
                        1f
                    )
                    .padding(end = 16.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_discount_svgrepo_com),
                contentDescription = null,
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier.size(48.dp)
            )
        }
        Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(percent = 50)) {
            Text(text = "Lihat Paket", style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun CounselorList(list: List<Counselor>) {
    Column(
        Modifier.padding(top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Konselor Pilihan", style = MaterialTheme.typography.subtitle1)
        list.forEach { item ->
            CounselorRow(item)
        }
    }
}

@Composable
fun CounselorRow(c: Counselor) {
    val image: Painter = painterResource(id = c.avatar)
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 8.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 24.dp)
        ) {
            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = CircleShape),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(Modifier.padding(bottom = 24.dp)) {
                    Text(text = c.name, Modifier.padding(end = 8.dp))
                    Text(text = c.title, style = MaterialTheme.typography.body2)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    c.specialist.forEach { item ->
                        TagText(text = item)
                    }
                }

            }
        }
    }
}

@Composable
fun TagText(text: String) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50))
            .background(color = MaterialTheme.colors.primary)
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onPrimary,
            fontSize = 11.sp
        )
    }
}

data class StorySlide(val image: Int, val title: String? = "")

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Stories() {
    val storyList = listOf(
        StorySlide(
            title = "Sering Gelisah Saat Mau Tidur? Atasi dengan Ini!",
            image = R.drawable.story_1
        ),
        StorySlide(
            title = "8 Penyebab Stres Mahasiswa, Bukan Cuma Urusan Kuliah",
            image = R.drawable.story2
        ),
        StorySlide(
            title = "Mengurangi Stress Ibu Hamil, Coba Cara Mudah Ini!",
            image = R.drawable.story3
        ),
        StorySlide(
            title = "7 Tips Mendidik Anak Remaja Perempuan, Susah-Susah Gampang",
            image = R.drawable.story4
        )
    )
    val pagerState = rememberPagerState(
        storyList.size,
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2500)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Column(Modifier.padding(top = 32.dp)) {
        Text(
            text = "BAIK-AN Stories",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Box {
            HorizontalPager(
                count = storyList.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
            ) { page ->
                StoryItem(
                    data = storyList[page],
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Podcast() {
    val storyList = listOf(
        StorySlide(
            image = R.drawable.podcast1
        ),
        StorySlide(
            image = R.drawable.podcast2
        ),
        StorySlide(
            image = R.drawable.podcast3
        ),
        StorySlide(
            image = R.drawable.podcast4
        )
    )

    val pagerState = rememberPagerState(
        storyList.size,
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2500)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Column(Modifier.padding(top = 32.dp, bottom = 24.dp)) {
        Text(
            text = "Event dan Podcast",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Box {
            HorizontalPager(
                count = storyList.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
            ) { page ->
                StoryItem(
                    data = storyList[page],
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    withPlayButton = true
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }
    }
}


@Composable
internal fun StoryItem(
    data: StorySlide,
    modifier: Modifier = Modifier,
    withPlayButton: Boolean = false
) {
    val image: Painter = painterResource(id = data.image)
    Box(
        modifier
    ) {
        Image(
            painter = image,
            contentScale = ContentScale.FillHeight,
            contentDescription = null,
            modifier = modifier,
        )
        if (!data.title.isNullOrBlank()) {
            Text(
                text = data.title,
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(bottom = 40.dp, start = 24.dp, end = 24.dp),
                color = Color.White,
                style = MaterialTheme.typography.subtitle1,
            )
        }
        if (withPlayButton) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .width(72.dp)
                    .height(48.dp)
                    .background(color = Color(0f, 0f, 0f, 0.4f))
                    .align(Alignment.Center),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_play_arrow_24),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color.White
                )
            }
        }
    }

}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun CounselingStep() {
    val steps = listOf(
        CounselingStep(
            title = "Daftar",
            description = "Daftar dan kamu akan dipasangkan dengan konselor kami"
        ),
        CounselingStep(
            title = "Tentukan Jadwal",
            description = "Bebas pilih waktu konselingmu sendiri (durasi 60 menit)"
        ),
        CounselingStep(
            title = "Sesi Konseling",
            description = "Chat, Voice Call, atau Video Call dengan konselor dan dapatkan saran terbaik"
        ),
    )
    val lazyListState = rememberLazyListState()
    LazyRow(
        state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(
            lazyListState = lazyListState,
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 32.dp)
    ) {
        items(items = steps, itemContent = { step ->
            Row(
                Modifier
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.primaryVariant)
                    .width(300.dp)
                    .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.primary)
                        .size(28.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${steps.indexOf(step) + 1}",
                        color = MaterialTheme.colors.onPrimary
                    )
                }
                Column(

                ) {
                    Text(text = step.title, Modifier.padding(bottom = 8.dp))
                    Text(text = step.description)
                }
            }
        })
    }
}

data class SelfHealingTopic(val img: Int, val title: String, val description: String)

@Composable
fun SelfHealingDialog(
    onDismissRequest: () -> Unit
) {
    val topics = listOf(
        SelfHealingTopic(
            R.drawable.meditation,
            "Self Meditation",
            "Meditasi yang bisa dilakukan sendiri dengan guideline audio BAIK-an"
        ),
        SelfHealingTopic(
            R.drawable.journal,
            "Journaling",
            "Panduan untuk membantu pengguna mencatat aktivitas-aktivitas yang telah dilakukan untuk mengelola stress"
        ),
        SelfHealingTopic(
            R.drawable.social_support,
            "Kelompok Dukungan Sosial",
            "Forum yang dibuat untuk bercerita mengenai berbagai jenis masalah antar pengguna aplikasi BAIK-an sebagai bentuk dukungan sosial"
        )
    )

    val (topic, setTopic) = remember {
        mutableStateOf<SelfHealingTopic?>(null)
    }

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            securePolicy = SecureFlagPolicy.Inherit
        ),
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                if (topic == null) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable {
                                setTopic(topics[0])
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.meditation),
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .padding(end = 8.dp)
                        )
                        Text(text = "Self Healing")
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable {
                                setTopic(topics[1])
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.journal),
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .padding(end = 8.dp)
                        )
                        Text(text = "Journaling")
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                setTopic(topics[2])
                            }, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.social_support),
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .padding(end = 8.dp)
                        )
                        Text(text = "Kelompok Dukungan Sosial")
                    }

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = { onDismissRequest() }) {
                            Text(text = "Close")
                        }
                    }

                } else {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = topic.title,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.subtitle1
                        )

                        Image(
                            painter = painterResource(id = topic.img),
                            contentDescription = null,
                            modifier = Modifier
                                .size(150.dp)
                                .padding(16.dp)
                        )

                        Text(
                            text = topic.description,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp),
                            textAlign = TextAlign.Center,
                        )

                        Button(onClick = {
                            setTopic(null)
                            onDismissRequest()
                        }) {
                            Text(text = "Close")
                        }
                    }
                }
            }
        }
    }
}


sealed class BoosterDialogType(val title: String, val desc: String) {
    object Sport : BoosterDialogType(
        "Sport",
        "BAIK-an bekerja sama dengan penyedia layanan dalam bentuk olahraga seperti :"
    )

    object Entertaiment : BoosterDialogType(
        "Entertaiment",
        "BAIK-an bekerja sama dengan penyedia layanan rekreasi dan refreshing meliputi :"
    )

    object Fnb : BoosterDialogType(
        "Food and Beverage",
        "BAIK-an bekerja sama dengan perusahaan penyedia makanan enak dan sehat"
    )
}

@Composable
fun BoosterDialog(
    onDismissRequest: () -> Unit,
    type: BoosterDialogType
) {
    val sportMenu = listOf(
        "Fitness Class" to R.drawable.ic_baseline_fitness_center_24,
        "Yoga Class" to R.drawable.ic_baseline_self_improvement_24,
        "FIBO" to R.drawable.fibo,
        "ROCCA" to R.drawable.rocca,
    )

    val entertaimentMenu = listOf(
        "Staycation" to R.drawable.ic_baseline_holiday_village_24,
        "Relaxation" to R.drawable.ic_baseline_nights_stay_24,
        "Recreation" to R.drawable.ic_baseline_forest_24,
        "Hobbies" to R.drawable.ic_baseline_sports_baseball_24,
    )

    val fnbMenu = listOf(
        "Heist.co" to 0,
        "Gourmet" to 0,
        "Comfort Food" to 0,
        "Passiterie" to 0,
    )

    val menu = when (type) {
        BoosterDialogType.Sport -> sportMenu
        BoosterDialogType.Entertaiment -> entertaimentMenu
        BoosterDialogType.Fnb -> fnbMenu
    }

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            securePolicy = SecureFlagPolicy.Inherit
        ),
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = type.title,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = type.desc,
                    Modifier.padding(bottom = 24.dp, top = 8.dp),
                    textAlign = TextAlign.Center
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    for (d in 0..1) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .border(
                                    width = 1.dp,
                                    shape = RoundedCornerShape(4.dp),
                                    color = Color.LightGray
                                )
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            if (menu[d].second != 0) {
                                Image(
                                    painter = painterResource(id = menu[d].second),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .padding(bottom = 8.dp)
                                )
                            }
                            Text(
                                text = menu[d].first,
                                modifier = Modifier.padding(vertical = if (menu[d].second != 0) 0.dp else 16.dp)
                            )
                        }
                    }
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    for (d in 2..3) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .border(
                                    width = 1.dp,
                                    shape = RoundedCornerShape(4.dp),
                                    color = Color.LightGray
                                )
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (menu[d].second != 0) {
                                Image(
                                    painter = painterResource(id = menu[d].second),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .padding(bottom = 8.dp)
                                )
                            }
                            Text(
                                text = menu[d].first,
                                modifier = Modifier.padding(vertical = if (menu[d].second != 0) 0.dp else 16.dp)
                            )
                        }
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        onDismissRequest()
                    }) {
                        Text(text = "Close")
                    }
                }
            }
        }
    }
}


@Preview(
    heightDp = 2000
)
@Composable
fun HomePromoBanner() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val toggleDrawer = {
        scope.launch {
            scaffoldState.drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }
    BaikanAndroidTheme {
        Surface {
            HomeScreen(toggleDrawer = toggleDrawer, goToCounseling = {})
        }
    }
}