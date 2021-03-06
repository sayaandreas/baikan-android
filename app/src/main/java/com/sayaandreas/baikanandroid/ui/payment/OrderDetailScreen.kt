package com.sayaandreas.baikanandroid.ui.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import com.sayaandreas.baikanandroid.R

@Composable
fun OrderDetailScreen(navController: NavHostController) {
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
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
        ) {
            Text(text = "Produk", style = MaterialTheme.typography.h6)
            Card(modifier = Modifier.padding(top = 16.dp)) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 24.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_auto_awesome_mosaic_24),
                            contentDescription = null
                        )
                        Text(
                            text = "Paket Nyaman",
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(28.dp)
                        )
                    }
                    Text(text = "Rp. 149.000")
                }
            }

            Text(
                text = "Info Pelanggan",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(top = 32.dp)
            )
            Card(elevation = 2.dp, modifier = Modifier.padding(top = 16.dp)) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Nama", color = Color.Gray)
                        Text(text = "Johny Pramono")
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Nomor HP", color = Color.Gray)
                        Text(text = "085272XXXXX")
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Email", color = Color.Gray)
                        Text(text = "johnyisworking@gmail.com")
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Jumlah Bayar", style = MaterialTheme.typography.h6)
                Text(text = "149.000", style = MaterialTheme.typography.h6)
            }
        }
        Row(Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                onClick = { navController.navigate(BaikanScreen.PaymentMethod.route) }) {
                Text(text = "Selanjutnya")
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