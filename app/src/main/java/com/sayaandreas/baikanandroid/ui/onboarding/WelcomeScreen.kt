package com.sayaandreas.baikanandroid.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
fun WelcomeScreen(navController: NavHostController) {
    val image: Painter = painterResource(id = R.drawable.welcome)
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primaryVariant)
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
        ) {
            Image(painter = image, contentDescription = null)
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
        ) {
            Text(text = "Halo Johny Pramono", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = "Selamat datang di", style = MaterialTheme.typography.h4)
            Text(
                text = "BAIK-AN",
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Semoga dengan bergabungnya kamu bersama kami, bisa bikin harimu lebih baik dengan memberikan solusi yang terbaik sesuai kebutuhanmu! \uD83D\uDE0A")
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 24.dp, end = 24.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(BaikanScreen.Home.route) {
                        popUpTo(BaikanScreen.Welcome.route) {
                            inclusive = true
                        }
                    }
                },
                shape = CircleShape,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Selanjutnya")
            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface() {
            WelcomeScreen(navController)
        }
    }
}