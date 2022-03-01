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
import com.sayaandreas.baikanandroid.BaikanScreen
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme

@Composable
fun ChooseBankScreen(navController: NavHostController) {
    val bca: Painter = painterResource(id = R.drawable.bca)
    val bri: Painter = painterResource(id = R.drawable.bri)
    val mandiri: Painter = painterResource(id = R.drawable.mandiri)
    val bni: Painter = painterResource(id = R.drawable.bni)
    val permata: Painter = painterResource(id = R.drawable.permata)
    val bersama: Painter = painterResource(id = R.drawable.bersama)
    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text(text = "Pilih Bank") }, navigationIcon = {
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
                text = "Pilih Bank yang akan digunakan",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .clickable {
                        navController.navigate(BaikanScreen.PaymentDetailScreen.name)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = bca,
                        contentDescription = "",
                        Modifier
                            .width(48.dp)
                            .padding(end = 16.dp)
                    )
                    Column() {
                        Text(text = "BCA")
                        Text(text = "Bayar di ATM BCA atau internet banking", fontSize = 12.sp)
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
                        painter = bri,
                        contentDescription = "",
                        Modifier
                            .width(48.dp)
                            .padding(end = 16.dp)
                    )
                    Column() {
                        Text(text = "BRI")
                        Text(text = "Bayar di ATM BRI atau internet banking", fontSize = 12.sp)
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
                        painter = mandiri,
                        contentDescription = "",
                        Modifier
                            .width(48.dp)
                            .padding(end = 16.dp)
                    )
                    Column() {
                        Text(text = "Mandiri")
                        Text(text = "Bayar di ATM Mandiri atau internet banking", fontSize = 12.sp)
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
                        painter = bni,
                        contentDescription = "",
                        Modifier
                            .width(48.dp)
                            .padding(end = 16.dp)
                    )
                    Column() {
                        Text(text = "BNI")
                        Text(text = "Bayar di ATM BNI atau internet banking", fontSize = 12.sp)
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
                        painter = permata,
                        contentDescription = "",
                        Modifier
                            .width(48.dp)
                            .padding(end = 16.dp)
                    )
                    Column() {
                        Text(text = "Permata")
                        Text(text = "Bayar di ATM Permata atau internet banking", fontSize = 12.sp)
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
                        painter = bersama,
                        contentDescription = "",
                        Modifier
                            .width(48.dp)
                            .padding(end = 16.dp)
                    )
                    Column() {
                        Text(text = "ATM Bersama")
                        Text(text = "Bayar di jaringan ATM bank lain", fontSize = 12.sp)
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
fun ChooseBankScreenPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme {
        Surface {
            ChooseBankScreen(navController)
        }
    }
}
