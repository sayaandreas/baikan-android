package com.sayaandreas.baikanandroid.ui.onboarding.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun RegisterGenderScreen(navController: NavHostController) {
    var (gender, setGender) = rememberSaveable { mutableStateOf("") }
    RegisterLayout(
        title = "Pilih gender Kamu",
        imageRes = R.drawable.onboarding_1,
        enableNext = gender != "",
        onSkipPressed = {
            navController.navigate(BaikanScreen.Home.route) {
                popUpTo(BaikanScreen.RegisterGender.route) {
                    inclusive = true
                }
            }
        },
        onNextPressed = {
            navController.navigate(BaikanScreen.RegisterPhone.route)
        }
    ) {
        Row(
            Modifier
                .fillMaxSize()
        ) {
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                GenderButton(
                    title = "Laki - laki",
                    selected = gender == "Laki - laki",
                    icon = R.drawable.ic_baseline_male_24,
                    onClick = { setGender(it) })
            }
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                GenderButton(title = "Perempuan", selected = gender == "Perempuan",
                    icon = R.drawable.ic_baseline_female_24,
                    onClick = { setGender(it) })
            }
        }
    }
}

@Composable
fun GenderButton(title: String, selected: Boolean, icon: Int, onClick: (g: String) -> Unit) {
    OutlinedButton(
        onClick = { onClick(title) },
        modifier = Modifier
            .width(120.dp)
            .height(140.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
        ),
        elevation = ButtonDefaults.elevation(0.dp),
        border = null,
        contentPadding = PaddingValues(8.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(
                        2.dp,
                        if (selected) MaterialTheme.colors.primary else Color.Black,
                        CircleShape
                    )
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.Center),
                    tint = if (selected) MaterialTheme.colors.primary else Color.Black
                )
            }
            Text(
                text = title,
                fontSize = 16.sp,
                color = if (selected) MaterialTheme.colors.primary else Color.Black
            )
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface() {
            RegisterGenderScreen(navController)
        }
    }
}