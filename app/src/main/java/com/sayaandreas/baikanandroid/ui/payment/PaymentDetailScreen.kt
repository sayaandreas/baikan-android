package com.sayaandreas.baikanandroid.ui.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
fun PaymentDetailScreen(navController: NavHostController) {
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
        TopAppBar(title = { Text(text = "Menunggu Pembayaran") }, navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        })
        Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
            Column(
                Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.primaryVariant)
                    .padding(16.dp)
            ) {
                Text(text = "Harap selesaikan pembayaran sebelum:")
                Text(text = "3 Februari 09:00 WIB")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Transfer pembayaran ke nomor rekening:")
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.bca),
                    contentDescription = null,
                    Modifier
                        .size(80.dp)
                        .padding(end = 16.dp)
                )
                Text(text = "037 701 000XXXXXX", style = MaterialTheme.typography.h5)
            }
            Text(text = "a/n PT. Baikan Cahaya Abadi")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Salin nomor rekeing", color = Color.Blue)
            Divider(Modifier.padding(vertical = 32.dp))
            Text(text = "Jumlah yang harus dibayar:")
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Rp 149.000",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5
            )
            Spacer(Modifier.height(16.dp))
            Text(text = "Salin jumlah", color = Color.Blue)
            Row(Modifier.padding(top = 52.dp)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    onClick = {
                        navController.navigate(BaikanScreen.PaymentSuccessScreen.name)
                    }) {
                    Text(text = "Konfirmasi Pembayaran")
                }
            }
        }
    }
}

@Preview
@Composable
fun PaymentDetailScreenPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme {
        Surface {
            PaymentDetailScreen(navController)
        }
    }
}
