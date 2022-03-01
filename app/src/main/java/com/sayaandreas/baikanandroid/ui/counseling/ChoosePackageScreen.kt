package com.sayaandreas.baikanandroid.ui.counseling

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.R

@Composable
fun ChoosePackageScreen(navController: NavHostController) {
    val image: Painter = painterResource(id = R.drawable.working)
    var (selectedTab, setSelectedTab) = rememberSaveable { mutableStateOf(1) }
    var (selectedCard, setSelectedCard) = rememberSaveable { mutableStateOf(0) }

    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text(text = "Paket Konseling") }, navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        })
        Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
            Text(text = "Pilih Paket Konseling", style = MaterialTheme.typography.h5)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Button(onClick = {
                    if (selectedTab != 1) {
                        setSelectedTab(1)
                        setSelectedCard(0)
                    }
                }, Modifier.weight(1f)) {
                    Text(text = "Text", fontSize = 11.sp)
                }
                Button(onClick = {
                    if (selectedTab != 2) {
                        setSelectedTab(2)
                        setSelectedCard(0)
                    }
                }, Modifier.weight(1f)) {
                    Text(text = "Call", fontSize = 11.sp)
                }
                Button(onClick = {
                    if (selectedTab != 3) {
                        setSelectedTab(3)
                        setSelectedCard(0)
                    }
                }, Modifier.weight(1f)) {
                    Text(text = "Video Call", fontSize = 11.sp)
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Icon(
                    Icons.Default.Info,
                    tint = MaterialTheme.colors.primary,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "Durasi Konseling Text per Sesi = 60 Menit")
            }
            Column(
                Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                when (selectedTab) {
                    1 -> {
                        Tab1(selectedCard, setSelectedCard)
                    }
                    2 -> {
                        Tab2(selectedCard, setSelectedCard)
                    }
                    3 -> {
                        Tab3(selectedCard, setSelectedCard)
                    }
                }
            }
            Row(Modifier.padding(vertical = 24.dp)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    onClick = {
                        if (selectedCard != 0) {
                            navController.navigate(BaikanScreen.ChooseCounselor.route)
                        }
                    }) {
                    Text(text = "Selanjutnya")
                }
            }
        }
    }
}

@Composable
fun Tab1(selectedCard: Int, onClick: (v: Int) -> Unit) {
    ProductCard(
        "Paket Perkenalan",
        "Rp 100.000/1x Sesi",
        "- Harga 100.000/sesi",
        "- Masa Berlaku 1 minggu",
        true,
        selectedCard == 1,
        1,
        onClick
    )
    ProductCard(
        "Paket Nyaman",
        "Rp 924.000/12x Sesi",
        "- Harga 77.000/sesi",
        "- Masa Berlaku 4 Bulan + Garansi uang kembali",
        false,
        selectedCard == 2,
        2,
        onClick
    )
}

@Composable
fun Tab2(selectedCard: Int, onClick: (v: Int) -> Unit) {
    ProductCard(
        "Paket Perkenalan",
        "Rp 149.000/1x Sesi",
        "- Harga 100.000/sesi",
        "- Masa Berlaku 1 minggu",
        true,
        selectedCard == 1,
        1,
        onClick
    )
    ProductCard(
        "Paket Nyaman",
        "Rp 550.000/4x Sesi",
        "- Harga 137.500/sesi",
        "- Masa Berlaku 1 minggu",
        false,
        selectedCard == 2,
        2,
        onClick
    )
}

@Composable
fun Tab3(selectedCard: Int, onClick: (v: Int) -> Unit) {
    ProductCard(
        "Paket Perkenalan",
        "Rp 300.000/1x Sesi",
        "- Harga 300.000/sesi",
        "- Masa Berlaku 1 minggu",
        true,
        selectedCard == 1,
        1,
        onClick,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductCard(
    name: String,
    price: String,
    desc1: String,
    desc2: String,
    firstimer: Boolean,
    selected: Boolean,
    num: Int,
    onClick: (v: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        border = BorderStroke(1.dp, color = MaterialTheme.colors.primary),
        backgroundColor = if (selected) MaterialTheme.colors.primaryVariant else Color.White,
        onClick = { onClick(num) }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.weight(1f)) {
                Text(text = name)
                Spacer(Modifier.size(4.dp))
                Text(
                    text = price,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (selected) MaterialTheme.colors.primary else Color.Black
                )
                Spacer(Modifier.size(4.dp))
                Text(text = desc1, fontSize = 11.sp)
                Text(text = desc2, fontSize = 11.sp)
            }
            TagText(firstimer)
        }

    }
}

@Composable
fun TagText(firstimer: Boolean) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50))
            .background(color = if (firstimer) MaterialTheme.colors.primary else Color.Green)
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        Text(
            text = if (firstimer) "For First Timer" else "Best Value",
            color = Color.White,
            fontSize = 11.sp
        )
    }
}

