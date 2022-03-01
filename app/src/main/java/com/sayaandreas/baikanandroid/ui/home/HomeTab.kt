package com.sayaandreas.baikanandroid.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import com.sayaandreas.baikanandroid.ui.theme.topAppBarLarge
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

data class Counselor(val name: String, val title: String, val specialist: List<String>, val avatar: Int)
data class CounselingStep(val title: String, val description: String)

@Composable
fun HomeScreen(toggleDrawer: () -> Job) {
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
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            modifier = Modifier
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
            contentColor = MaterialTheme.colors.onPrimary
        )
        Column(
            Modifier
                .padding(start = 18.dp, end = 16.dp, top = 32.dp)
        ) {
            MainMenu()

            PromoBanner()

            CounselorList(counselorList)

            Stories()

            CounselingStep()
        }
    }
}

@Composable
fun MainMenu() {
    val imgKonseling: Painter = painterResource(id = R.drawable.conversation)
    val imgJurnal: Painter = painterResource(id = R.drawable.journal)
    val imgMeditasi: Painter = painterResource(id = R.drawable.meditation)

    Text(text = "Butuh bantuan apa?", style = MaterialTheme.typography.subtitle1)
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = imgKonseling, contentDescription = "", Modifier.fillMaxWidth(.8f))
            Text(text = "Konseling")
        }
        Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = imgJurnal, contentDescription = "", Modifier.fillMaxWidth(.8f))
            Text(text = "Mood Jurnal")
        }
        Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = imgMeditasi, contentDescription = "", Modifier.fillMaxWidth(.8f))
            Text(text = "Meditasi")
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
        Row(Modifier.padding(vertical = 16.dp)) {
            Text(text = "Dengan paket konseling ini kamu mendapatkan sesi konseling dengan total harga yang lebih murah lho")
        }
        Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(percent = 50)) {
            Text(text = "Lihat Paket", style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun CounselorList(list: List<Counselor>) {
    Column(
        Modifier.padding(top = 24.dp),
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(.3f)
                    .height(80.dp)
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

@Composable
fun Stories() {
    val image: Painter = painterResource(id = R.drawable.story_1)
    Column(Modifier.padding(top = 24.dp)) {
        Text(text = "BAIK-AN Stories")
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun CounselingStep() {
    val steps = listOf(
        CounselingStep(title = "Daftar", description = "Daftar dan kamu akan dipasangkan dengan konselor kami"),
        CounselingStep(title = "Tentukan Jadwal", description = "Bebas pilih waktu konselingmu sendiri (durasi 60 menit)"),
        CounselingStep(title = "Sesi Konseling", description = "Chat, Voice Call, atau Video Call dengan konselor dan dapatkan saran terbaik"),
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
        items(items = steps, itemContent =  { step ->
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
            HomeScreen(toggleDrawer)
        }
    }
}