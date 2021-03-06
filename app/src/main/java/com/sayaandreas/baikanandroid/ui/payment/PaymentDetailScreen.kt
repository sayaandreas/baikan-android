package com.sayaandreas.baikanandroid.ui.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme

@Composable
fun PaymentDetailScreen(navController: NavHostController) {
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
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
        ) {
            Column(
                Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(color = Color(255, 232, 169))
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
            Text(text = "Salin nomor rekeing", textDecoration = TextDecoration.Underline)
            Divider(Modifier.padding(vertical = 32.dp))
            Text(text = "Jumlah yang harus dibayar:")
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Rp 149.000",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5
            )
            Spacer(Modifier.height(16.dp))
            Text(text = "Salin jumlah", textDecoration = TextDecoration.Underline)
            Spacer(Modifier.height(24.dp))
        }
        Row(Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                onClick = {
                    navController.navigate(BaikanScreen.PaymentSuccess.route)
                }) {
                Text(text = "Konfirmasi Pembayaran")
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
