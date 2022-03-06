package com.sayaandreas.baikanandroid.ui.home

import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.model.Counselor
import com.sayaandreas.baikanandroid.model.User
import com.sayaandreas.baikanandroid.ui.main.MainViewModel
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import com.sayaandreas.baikanandroid.ui.theme.topAppBarLarge
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield


data class CounselingStep(val title: String, val description: String)

@Composable
fun HomeTab(
    goToCounseling: () -> Unit,
    toggleDrawer: () -> Job,
    counselorList: List<Counselor>,
    currentUser: User? = null,
    goToCounselorDetail: (c: Counselor) -> Unit
) {
    val scrollState = rememberScrollState(0)
    val (showDialog, setShowDialog) = remember {
        mutableStateOf(false)
    }
    val (showBoosterDialog, setShowBoosterDialog) = remember {
        mutableStateOf<BoosterDialogType?>(null)
    }
    val username = currentUser?.name ?: "Guest"

    Column() {
        TopAppBar(
            modifier = Modifier
                .zIndex(10f)
                .height(170.dp)
                .clip(MaterialTheme.shapes.topAppBarLarge),

            content = {
                Column(Modifier.fillMaxWidth()) {
                    Row {
                        IconButton(onClick = { toggleDrawer() }) {
                            Icon(Icons.Filled.Menu, contentDescription = null, tint = Color.White)
                        }
                    }
                    Column(Modifier.padding(start = 14.dp, end = 14.dp, top = 16.dp)) {
                        Text(
                            text = "Halo, $username",
                            style = MaterialTheme.typography.h4,
                            color = Color.White
                        )
                        Text(text = "You will be fine, even finer", color = Color.White)
                    }
                }
            },
            contentColor = Color.White,
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
            CounselorList(counselorList, goToDetail = goToCounselorDetail)
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

    Text(text = "Butuh bantuan apa?", style = MaterialTheme.typography.h6)
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 32.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier
                .weight(1f)
                .aspectRatio(7 / 8f)
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(24.dp)
                )
                .clickable {
                    goToCounseling()
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_menu_counseling),
                contentDescription = "",
                Modifier
                    .fillMaxSize(.7f)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = "Konseling",
                style = MaterialTheme.typography.subtitle2,
                color = Color.White
            )
        }
        Column(
            Modifier
                .weight(1f)
                .aspectRatio(7 / 8f)
                .background(
                    color = MaterialTheme.colors.secondary,
                    shape = RoundedCornerShape(24.dp)
                )
                .clickable {
                    showMeditation()
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.meditation),
                contentDescription = "",
                Modifier
                    .fillMaxSize(.7f)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = "Self Healing",
                style = MaterialTheme.typography.subtitle2,
                color = Color.White
            )
        }
    }
}

data class ClickableBoosterMenu(
    val title: String,
    @DrawableRes val icon: Int,
    val type: BoosterDialogType
)

@Composable
fun BoosterMenu(setShowDialog: (type: BoosterDialogType) -> Unit) {
    val menuList = listOf(
        ClickableBoosterMenu(
            title = "Sport",
            icon = R.drawable.ic_ic_menu_sport,
            BoosterDialogType.Sport
        ),
        ClickableBoosterMenu(
            title = "Entertaiment",
            icon = R.drawable.ic_menu_entertaiment,
            BoosterDialogType.Entertaiment
        ),
        ClickableBoosterMenu(title = "F&B", icon = R.drawable.ic_menu_food, BoosterDialogType.Fnb)
    )
    Text(text = "Ingin mengurangi stres mu?", style = MaterialTheme.typography.h6)
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        menuList.forEach {
            Column(
                Modifier
                    .weight(1f)
                    .aspectRatio(7 / 8f)
                    .clickable {
                        setShowDialog(it.type)
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Center
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(.7f)
                        .aspectRatio(1f)
                        .border(2.dp, MaterialTheme.colors.primary, CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize(.5f),
                        tint = MaterialTheme.colors.primary
                    )
                }
                Text(
                    text = it.title,
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.subtitle2
                )
            }
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
fun CounselorList(list: List<Counselor>, goToDetail: (c: Counselor) -> Unit) {
    Column(
        Modifier.padding(top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Konselor Pilihan", style = MaterialTheme.typography.subtitle1)
        list.forEach { item ->
            CounselorRow(item, selectCounselor = goToDetail)
        }
    }
}

@Composable
fun CounselorRow(c: Counselor, selectCounselor: (c: Counselor) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                selectCounselor(c)
            },
        elevation = 8.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 24.dp)
        ) {
            AsyncImage(
                model = c.image,
                contentDescription = null,
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
                Column(Modifier.padding(bottom = 8.dp)) {
                    Text(text = c.fullName, Modifier.padding(end = 8.dp))
                }
                FlowRow(crossAxisSpacing = 4.dp, mainAxisSpacing = 4.dp) {
                    c.specialist.forEach { item ->
                        TagText(text = item.title)
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
fun HomeTabPreview() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val toggleDrawer = {
        scope.launch {
            scaffoldState.drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }
    val mainViewModel: MainViewModel = viewModel()
    BaikanAndroidTheme {
        Surface {
            HomeTab(
                goToCounseling = {},
                toggleDrawer = toggleDrawer,
                mainViewModel.counselorList,
                goToCounselorDetail = {})
        }
    }
}