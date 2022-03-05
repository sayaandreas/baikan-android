package com.sayaandreas.baikanandroid.ui.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme

@Composable
fun PaymentSuccessScreen(navController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            2.dp,
                            color = MaterialTheme.colors.primaryVariant,
                            shape = CircleShape
                        )
                        .padding(4.dp)
                        .border(
                            3.dp,
                            color = MaterialTheme.colors.primaryVariant,
                            shape = CircleShape
                        )
                        .padding(6.dp)
                        .clip(shape = CircleShape)
                        .background(color = MaterialTheme.colors.primary)
                        .padding(24.dp)

                ) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = "Pembayaran Berhasil",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.primary
                )
            }

            Card(elevation = 4.dp, modifier = Modifier.padding(top = 32.dp)) {
                Row(
                    Modifier
                        .clip(shape = MaterialTheme.shapes.large)
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Paket", style = MaterialTheme.typography.subtitle1)
                        Text(text = "Nyaman", style = MaterialTheme.typography.h4)
                        Text(
                            text = "Berlaku hingga 31 Desember 2022",
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                    Column {
                        TagText("1X")
                    }
                }

            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
            ) {
                Button(
                    onClick = {
                        navController.navigate(BaikanScreen.CallCounselor.route)
                    },
                    Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "Konsultasi Sekarang",
                    )
                }

                OutlinedButton(
                    onClick = { navController.navigate(BaikanScreen.Home.route) },
                    Modifier.fillMaxWidth()
                ) {
                    Text(text = "Kembali ke Home", color = Color.Black)
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
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onPrimary,
        )
    }
}


@Preview
@Composable
fun PaymentSuccessPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface {
            PaymentSuccessScreen(navController)
        }
    }
}

