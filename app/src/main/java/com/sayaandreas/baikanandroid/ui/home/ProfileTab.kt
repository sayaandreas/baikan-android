package com.sayaandreas.baikanandroid.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.model.Counselor
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.ui.main.MainViewModel
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import com.sayaandreas.baikanandroid.ui.theme.topAppBarLarge

@Composable
fun ProfileTab(navController: NavController, mainViewModel: MainViewModel) {
    val tabs = listOf("Konseling", "Langganan", "Statistik")
    val (selectedTab, setSelectedTab) = remember { mutableStateOf("Konseling") }

    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            modifier = Modifier
                .zIndex(10f)
                .height(180.dp)
                .clip(MaterialTheme.shapes.topAppBarLarge),

            content = {
                val image: Painter = painterResource(id = R.drawable.counselor1)
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = image,
                        contentDescription = "",
                        modifier = Modifier
                            .size(90.dp)
                            .clip(shape = CircleShape)

                    )
                    Text(
                        text = mainViewModel.currentUser.value?.name ?: "Guest",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            },
            contentColor = MaterialTheme.colors.onPrimary
        )
        Row(
            Modifier
                .padding(top = 24.dp)
                .height(56.dp)
        ) {
            tabs.forEach {
                val selected = selectedTab == it
                val color = if (selected) MaterialTheme.colors.primary else Color.LightGray
                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .drawBehind {
                            val strokeWidth = 2 * density
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = color,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        }
                        .clickable {
                            setSelectedTab(it)
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = it, color = color)
                }
            }
        }
        Column(
            Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .verticalScroll(rememberScrollState())
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            when (selectedTab) {
                "Konseling" -> CounselingTab(counselor = Counselor.getAll().first(), logout = {
                    mainViewModel.logoutUser()
                    navController.navigate(BaikanScreen.Login.route) {
                        popUpTo(BaikanScreen.Login.route) {
                            inclusive = true
                        }
                    }
                })
                "Langganan" -> LanggananTab()
                "Statistik" -> StatisticTab()
            }
        }
    }
}

@Composable
fun CounselingTab(counselor: Counselor, logout: () -> Unit) {
    val specialist = counselor.specialist.joinToString(separator = ", ", transform = {
        it.title
    })
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Text(
            text = "Jadwal Konseling Terbaru",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(), elevation = 4.dp
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Column(
                    Modifier
                        .padding(end = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = counselor.image,
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(shape = CircleShape),
                    )
                }
                Column() {
                    Text(
                        text = counselor.fullName,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "1 sesi konseling",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_calendar_today_24),
                            contentDescription = null,
                            tint = MaterialTheme.colors.secondary
                        )
                        Text(text = "Rabu, 09 Maret 2022")
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_access_time_24),
                            contentDescription = null,
                            tint = MaterialTheme.colors.secondary
                        )
                        Text(text = "18.00 - 19.00 WIB")
                    }
                }
            }

        }
    }
}

@Composable
fun LanggananTab() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.undraw_no_data_re_kwbl),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 16.dp), tint = MaterialTheme.colors.primary
        )
        Text(text = "Kamu belum punya paket langganan", style = MaterialTheme.typography.h6)
    }
}

@Composable
fun StatisticTab() {
    Stats()
    MindfulnessStats()
    Trophies()
}

@Composable
fun Stats() {
    val imageFire: Painter = painterResource(id = R.drawable.fire)
    val imageSpin: Painter = painterResource(id = R.drawable.spin)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Streak Harian")
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp), horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = imageFire,
                        contentDescription = "",
                        modifier = Modifier
                            .size(60.dp)

                    )
                    Text(text = "0/3", style = MaterialTheme.typography.h4)
                }
            }
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Lucky Spin")
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp), horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = imageSpin,
                        contentDescription = "",
                        modifier = Modifier
                            .size(60.dp)

                    )
                    Text(text = "0/3", style = MaterialTheme.typography.h4)
                }

            }
        }
    }
}

@Composable
fun MindfulnessStats() {
    val imageHourglass: Painter = painterResource(id = R.drawable.hourglass)
    val imageTime: Painter = painterResource(id = R.drawable.time)
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Mindfulness Statistik", style = MaterialTheme.typography.subtitle1)
        Text(
            text = "Lihat  semua",
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.button
        )
    }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            Modifier
                .weight(1f)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "0",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row {
                    Image(
                        painter = imageHourglass,
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 8.dp)
                    )
                    Text(text = "Jumlah sesi")
                }
            }
        }

        Card(
            Modifier
                .weight(1f)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "0",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row() {
                    Image(
                        painter = imageTime,
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 8.dp)
                    )
                    Text(text = "Menit mindful")
                }

            }
        }
    }
}

@Composable
fun Trophies() {
    val image: Painter = painterResource(id = R.drawable.champ)
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        Text(
            text = "Berdasarkan menit",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 16.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 4.dp
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = image,
                        contentDescription = "",
                        modifier = Modifier
                            .size(85.dp)
                    )
                    Text(text = "Pemula", textAlign = TextAlign.Center)
                    Text(text = "Pemberani", textAlign = TextAlign.Center)
                }
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = image,
                        contentDescription = "",
                        modifier = Modifier
                            .size(85.dp)
                    )
                    Text(text = "Petualang", maxLines = 2, textAlign = TextAlign.Center)
                    Text(text = "Gigih", maxLines = 2, textAlign = TextAlign.Center)
                }
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = image,
                        contentDescription = "",
                        modifier = Modifier
                            .size(85.dp)
                    )
                    Text(text = "Penguasa", maxLines = 2, textAlign = TextAlign.Center)
                    Text(text = "Lahan", maxLines = 2, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Preview(
    heightDp = 1000
)
@Composable
fun ProfileScreenScreenPreview() {
    val navController = rememberNavController()
    val mainViewModel = MainViewModel(null)
    BaikanAndroidTheme {
        Surface {
            ProfileTab(navController, mainViewModel)
        }
    }
}