package com.sayaandreas.baikanandroid.ui.counseling

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.R

@Composable
fun TopicIntroScreen(navController: NavHostController) {
    val image: Painter = painterResource(id = R.drawable.working)
    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text(text = "Konseling") }, navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        })
        Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
            Image(
                painter = image,
                contentDescription = "",
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1 / 1f)
                    .padding(bottom = 8.dp)
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 13.sp,
                lineHeight = 16.sp,
                text = "Konseling Pekerjaan bermanfaat untuk membantu kamu mengidentifikasi dan memahami isu dan potensi dalam karir secara objektif. Beberapa contoh hal yang dapat dibahas adalah:",
                style = MaterialTheme.typography.h6
            )
            Row(Modifier.padding(4.dp), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    fontSize = 12.sp,
                    text = "1."
                )
                Text(
                    fontSize = 12.sp,
                    text = "Explorasi minat dan bakat untuk menemukan passion dan aspirasi kamu"
                )
            }
            Row(Modifier.padding(4.dp), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    fontSize = 12.sp,
                    text = "2."
                )
                Text(
                    fontSize = 12.sp,
                    text = "Explorasi minat dan bakat untuk menemukan passion dan aspirasi kamu"
                )
            }
            Row(Modifier.padding(4.dp), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    fontSize = 12.sp,
                    text = "3."
                )
                Text(
                    fontSize = 12.sp,
                    text = "Membantu mengidentifikasikan pikiran dan tingkah laku negatif yang menghambat karirmu"
                )
            }
            Row(Modifier.padding(vertical = 24.dp)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    onClick = { navController.navigate(BaikanScreen.ChooseService.route) }) {
                    Text(text = "Selanjutnya")
                }
            }
        }
    }
}

