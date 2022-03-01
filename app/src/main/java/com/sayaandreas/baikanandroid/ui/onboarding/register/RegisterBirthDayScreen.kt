package com.sayaandreas.baikanandroid.ui.onboarding.register

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun RegisterBirthDayScreen(navController: NavHostController) {
    val context = LocalContext.current
    var (dob, setDob) = rememberSaveable { mutableStateOf("") }
    var (showDialog, setShowDialog) = rememberSaveable { mutableStateOf(false) }
    var dateFormat = "yyyy-MM-dd"

    RegisterLayout(
        title = "Masukkan tanggal lahir Kamu",
        imageRes = R.drawable.onboarding_1,
        enableNext = dob != "",
        onBackPressed = {},
        onSkipPressed = {},
        onNextPressed = {
            navController.navigate(BaikanScreen.RegisterAddress.route)
        },
        content = {
            OutlinedTextField(
                value = dob,
                onValueChange = setDob,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showDatePickerDialog(context, dateFormat, dob.toString(), setDob)
                    },
                placeholder = { Text(text = "Tanggal lahir") },
                shape = RoundedCornerShape(50),
            )
        })
}

fun showDatePickerDialog(
    context: Context,
    dateFormat: String,
    dateOfBirth: String,
    setDob: (v: String) -> Unit
) {
    val calendar = getCalendar(dateFormat, dateOfBirth)
    DatePickerDialog(
        context, { _, year, month, day ->
            setDob(getPickedDateAsString(dateFormat, year, month, day))
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
        .show()
}

private fun getCalendar(dateFormat: String, dateOfBirth: String): Calendar {
    return if (dateOfBirth.isEmpty())
        Calendar.getInstance()
    else
        getLastPickedDateCalendar(dateFormat, dateOfBirth)
}


private fun getLastPickedDateCalendar(dateFormat: String, dateOfBirth: String): Calendar {
    val dateFormat = SimpleDateFormat(dateFormat)
    val calendar = Calendar.getInstance()
    calendar.time = dateFormat.parse(dateOfBirth)
    return calendar
}

private fun getPickedDateAsString(dateFormat: String, year: Int, month: Int, day: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val dateFormat = SimpleDateFormat(dateFormat)
    return dateFormat.format(calendar.time)
}
