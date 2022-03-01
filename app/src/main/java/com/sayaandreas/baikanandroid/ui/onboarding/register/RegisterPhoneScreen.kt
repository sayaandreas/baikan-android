package com.sayaandreas.baikanandroid.ui.onboarding.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
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
fun RegisterPhoneScreen(navController: NavHostController) {
    var (phone, setPhone) = rememberSaveable { mutableStateOf("") }
    RegisterLayout(
        title = "Masukkan nomor HP Kamu",
        imageRes = R.drawable.onboarding_1,
        enableNext = phone != "",
        onBackPressed = {},
        onSkipPressed = {},
        onNextPressed = {
            navController.navigate(BaikanScreen.RegisterBirthDay.route)
        },
        content = {
            OutlinedTextField(
                value = phone,
                placeholder = { Text(text = "+62") },
                onValueChange = {
                    setPhone(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                shape = RoundedCornerShape(50),
            )
        })
}


@Preview
@Composable
fun RegisterPhoneScreenPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface() {
            RegisterPhoneScreen(navController)
        }
    }
}
