package com.sayaandreas.baikanandroid.ui.onboarding.register

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme

@Composable
fun RegisterAddressScreen(navController: NavHostController) {
    var (expanded, setExpanded) = rememberSaveable { mutableStateOf(false) }
    var (selected, setSelected) = rememberSaveable { mutableStateOf(0) }
    val items = listOf("Alamat", "Jakarta", "Bandung", "Surabaya", "Makasar", "Medan", "Semarang")

    RegisterLayout(
        title = "Masukkan alamat Kamu",
        imageRes = R.drawable.onboarding_1,
        enableNext = selected != 0,
        onBackPressed = {},
        onSkipPressed = {},
        onNextPressed = {
            navController.navigate(BaikanScreen.Welcome.route)
        },
        content = {
            OutlinedTextField(
                value = items[selected],
                onValueChange = {},
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { setExpanded(true) }),
                shape = RoundedCornerShape(50),
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                items.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        setSelected(index)
                        setExpanded(false)
                    }) {
                        Text(text = s)
                    }
                }
            }
        })
}

@Preview
@Composable
fun RegisterAddressScreenPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface() {
            RegisterAddressScreen(navController)
        }
    }
}