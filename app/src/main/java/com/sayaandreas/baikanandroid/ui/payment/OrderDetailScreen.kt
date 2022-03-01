package com.sayaandreas.baikanandroid.ui.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.BaikanScreen
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme

@Composable
fun OrderDetailScreen(navController: NavHostController) {
    val checkedState = remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text(text = "Order Detail") }, navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        })
        Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
            Text(text = "Order ID: SLJX-WEOPL-SWRFK-SLSIK")
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(color = Color.LightGray)
            ) {
                Column(Modifier.weight(6f)) {
                    Text(text = "Produk")
                }
                Column(Modifier.weight(2f)) {
                    Text(text = "Qty")
                }
                Column(Modifier.weight(3f)) {
                    Text(text = "Jumlah")
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Column(Modifier.weight(6f)) {
                    Text(text = "Paket Nyaman")
                }
                Column(Modifier.weight(2f)) {
                    Text(text = "1")
                }
                Column(Modifier.weight(3f)) {
                    Text(text = "149.000")
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(Modifier.weight(6f)) {
                    Text(text = "Total")
                }
                Column(Modifier.weight(2f)) {
                    Text(text = "")
                }
                Column(Modifier.weight(3f)) {
                    Text(text = "149.000")
                }
            }

            Row(Modifier.padding(top = 24.dp, bottom = 8.dp)) {
                Text(text = "Info Pelanggan")
            }
            Divider(Modifier.padding(bottom = 8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column() {
                    Text(text = "Nama")
                }
                Column() {
                    Text(text = "Johny Pramono")
                }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column() {
                    Text(text = "Email")
                }
                Column() {
                    Text(text = "johnypramono@gmail.com")
                }
            }

            Row(Modifier.padding(top = 32.dp)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    onClick = { navController.navigate(BaikanScreen.PaymentMethodScreen.name) }) {
                    Text(text = "Selanjutnya")
                }
            }
        }
    }
}

@Preview
@Composable
fun CounselingAgreementPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme {
        Surface {
            OrderDetailScreen(navController)
        }
    }
}