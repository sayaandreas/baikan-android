package com.sayaandreas.baikanandroid.ui.counseling

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme

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
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
        ) {
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
                text = "Konseling Pekerjaan bermanfaat untuk membantu kamu mengidentifikasi dan memahami isu dan potensi dalam karir secara objektif. Beberapa contoh hal yang dapat dibahas adalah:",
                style = MaterialTheme.typography.subtitle2
            )
            Row(Modifier.padding(4.dp), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "1."
                )
                Text(
                    text = "Explorasi minat dan bakat untuk menemukan passion dan aspirasi kamu"
                )
            }
            Row(Modifier.padding(4.dp), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "2."
                )
                Text(
                    text = "Explorasi minat dan bakat untuk menemukan passion dan aspirasi kamu"
                )
            }
            Row(Modifier.padding(4.dp), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "3."
                )
                Text(
                    text = "Membantu mengidentifikasikan pikiran dan tingkah laku negatif yang menghambat karirmu"
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                onClick = { navController.navigate(BaikanScreen.ChooseService.route) }) {
                Text(text = "Selanjutnya")
            }
        }
    }
}

@Preview
@Composable
fun TopicIntroPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface() {
            TopicIntroScreen(navController)
        }
    }
}