package com.sayaandreas.baikanandroid.ui.onboarding.register

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegisterAddressScreen(navController: NavHostController) {
    val items = listOf("Jakarta", "Bandung", "Surabaya", "Makasar", "Medan", "Semarang")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(items[0]) }

    RegisterLayout(
        title = "Masukkan alamat Kamu",
        imageRes = R.drawable.onboarding_1,
        enableNext = selectedOptionText != "",
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
            navController.navigate(BaikanScreen.Welcome.route) {
                popUpTo(BaikanScreen.RegisterGender.route) {
                    inclusive = true
                }
            }
        }
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            Modifier.fillMaxWidth(),
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text("Kota") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                items.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
    }
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