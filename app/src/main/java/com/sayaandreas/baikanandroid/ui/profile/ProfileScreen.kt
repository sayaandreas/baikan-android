package com.sayaandreas.baikanandroid.ui.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import com.sayaandreas.baikanandroid.ui.theme.topAppBarLarge

@Composable
fun ProfileScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            modifier = Modifier
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
                        text = "Halo, Johny Pramono",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            },
            contentColor = MaterialTheme.colors.onPrimary
        )
        Column(Modifier.padding(top = 16.dp)) {
            SubscriptionButton()
            Stats()
            MindfulnessStats()
            Trophies()

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 16.dp)
            ) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    border = BorderStroke(1.dp, color = MaterialTheme.colors.primary)
                ) {
                    Text(text = "Logout")
                }
            }
        }
    }
}

@Composable
fun SubscriptionButton() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_folder_open_24),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
                Text(text = "Langganan Saya", Modifier.padding(start = 8.dp))
            }
            Icon(Icons.Filled.ArrowForward, contentDescription = null)
        }
    }
}

@Composable
fun Stats() {
    val imageFire: Painter = painterResource(id = R.drawable.fire)
    val imageSpin: Painter = painterResource(id = R.drawable.spin)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(16.dp)
    ) {
        Row(
            Modifier
                .padding(16.dp),
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
                    Text(text = "0", style = MaterialTheme.typography.h4)
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
                .padding(16.dp)
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
                    Text(text = "Pemula Pemberani", textAlign = TextAlign.Center)
                }
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = image,
                        contentDescription = "",
                        modifier = Modifier
                            .size(85.dp)
                    )
                    Text(text = "Petualang Gigih", maxLines = 2, textAlign = TextAlign.Center)
                }
                Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = image,
                        contentDescription = "",
                        modifier = Modifier
                            .size(85.dp)
                    )
                    Text(text = "Penguasa Lahan", maxLines = 2, textAlign = TextAlign.Center)
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
    BaikanAndroidTheme {
        Surface {
            ProfileScreen()
        }
    }
}