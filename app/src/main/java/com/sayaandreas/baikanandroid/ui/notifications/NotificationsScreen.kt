package com.sayaandreas.baikanandroid.ui.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import java.util.*

data class Notification(
    val date: Date,
    val title: String,
    val description: String,
    val read: Boolean = true
)

@Composable
fun NotificationsScreen() {
    val notificationList = listOf(
        Notification(
            date = Date(),
            title = "Pembelian Berhasil",
            description = "Anda Telah melakukan transaksi sebesar Rp250.000 dennga memesan Paket Nyaman sebesar Rp150.000",
            read = false
        ),
        Notification(
            date = Date(),
            title = "Pembelian Berhasil",
            description = "Anda Telah melakukan transaksi sebesar Rp250.000 dennga memesan Paket Nyaman sebesar Rp150.000",
            read = false
        ),
        Notification(
            date = Date(),
            title = "Artikel Terbaru",
            description = "Sering Gelisah Saat Mau Tidur? Atasi dengan Ini!"
        ),
        Notification(
            date = Date(),
            title = "Artikel Terbaru",
            description = "8 Penyebab Stres Mahasiswa, Bukan Cuma Urusan Kuliah"
        ),
        Notification(
            date = Date(),
            title = "Pembelian Berhasil",
            description = "Anda Telah melakukan transaksi sebesar Rp250.000 dennga memesan Paket Nyaman sebesar Rp150.000"
        ),
        Notification(
            date = Date(),
            title = "Pembelian Berhasil",
            description = "Anda Telah melakukan transaksi sebesar Rp250.000 dennga memesan Paket Nyaman sebesar Rp150.000"
        ),
    )
    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text(text = "Notifications") })
        LazyColumn() {
            items(items = notificationList) { notification ->
                Row(
                    Modifier
                        .background(color = if (notification.read) MaterialTheme.colors.background else MaterialTheme.colors.secondaryVariant)
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Icon(Icons.Filled.Notifications, contentDescription = null)
                    }
                    Column {
                        Text(text = notification.date.toString(), fontSize = 11.sp)
                        Text(text = notification.title)
                        Text(text = notification.description, fontSize = 11.sp, lineHeight = 16.sp)
                    }
                }

            }
        }
    }
}

@Preview(
    heightDp = 800
)
@Composable
fun NotificationsScreenPreview() {
    BaikanAndroidTheme {
        Surface {
            NotificationsScreen()
        }
    }
}