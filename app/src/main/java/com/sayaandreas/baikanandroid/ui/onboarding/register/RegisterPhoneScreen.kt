package com.sayaandreas.baikanandroid.ui.onboarding.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        onBackPressed = {
            navController.popBackStack()
        },
        onSkipPressed = {
            navController.navigate(BaikanScreen.Home.route) {
                popUpTo(BaikanScreen.RegisterGender.route) {
                    inclusive = true
                }
            }
        },
        onNextPressed = {
            navController.navigate(BaikanScreen.RegisterBirthDay.route)
        }
    ) {
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
    }
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
