package com.sayaandreas.baikanandroid.ui.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme

@Composable
fun PaymentMethodScreen(navController: NavHostController) {
    val trf: Painter = painterResource(id = R.drawable.transfer)
    val cc: Painter = painterResource(id = R.drawable.cc)
    val gopay: Painter = painterResource(id = R.drawable.gopay)
    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text(text = "Metode Pembayaran") }, navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        })
        Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
            Text(
                text = "Pilih Metode Pembayaran",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .clickable {
                        navController.navigate(BaikanScreen.ChooseBank.route)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = trf,
                        contentDescription = "",
                        Modifier
                            .width(48.dp)
                            .padding(end = 16.dp)
                    )
                    Column() {
                        Text(text = "ATM/Bank Transfer")
                        Text(text = "Bayar di ATM Bersama, Prima atau Alto", fontSize = 12.sp)
                    }
                }
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_arrow_forward_ios_24),
                    contentDescription = null,
                    Modifier
                        .size(32.dp)
                        .padding(start = 16.dp)
                )
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = cc,
                        contentDescription = "",
                        Modifier
                            .width(48.dp)
                            .padding(end = 16.dp)
                    )
                    Column() {
                        Text(text = "Kartu Kredit/Debit")
                        Text(text = "Bayar di ATM Bersama, Prima atau Alto", fontSize = 12.sp)
                    }
                }
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_arrow_forward_ios_24),
                    contentDescription = null,
                    Modifier
                        .size(32.dp)
                        .padding(start = 16.dp)
                )
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = gopay,
                        contentDescription = "",
                        Modifier
                            .width(48.dp)
                            .padding(end = 16.dp)
                    )
                    Column() {
                        Text(text = "Gopay")
                        Text(text = "Bayar dengan saldo Gopay anda", fontSize = 12.sp)
                    }
                }
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_arrow_forward_ios_24),
                    contentDescription = null,
                    Modifier
                        .size(32.dp)
                        .padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PaymentMethodScreenPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme {
        Surface {
            PaymentMethodScreen(navController)
        }
    }
}
