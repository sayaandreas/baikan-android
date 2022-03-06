package com.sayaandreas.baikanandroid.ui.counseling

import android.widget.Space
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun ChooseServiceScreen(navController: NavHostController) {
    var (selectedService, setSelectedService) = rememberSaveable { mutableStateOf("") }
    val image: Painter = painterResource(id = R.drawable.doctors)
    val checkList = listOf(
        "Telah mendapat izin konseling dari HIMPSI",
        "Direkomendasikan untuk kamu yang memiliki masalah cukup berat hingga rumit",
        "Direkomendasikan untuk kamu yang memiliki masalah cukup berat hingga rumit"
    )

    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text(text = "Pilih Layanan") }, navigationIcon = {
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
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = image,
                contentDescription = "",
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1 / 1f)
                    .padding(bottom = 24.dp)
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                checkList.forEach {
                    Row(
                        Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                        Text(
                            text = it
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = "Pilih Jenis Layanan",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if (selectedService === "1") MaterialTheme.colors.primary else Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            setSelectedService("1")
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = selectedService === "1",
                        onClick = { setSelectedService("1") },
                        colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary),
                    )
                    Text(text = "Psikolog (Lulusan S2 Psikologi)")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if (selectedService === "2") MaterialTheme.colors.primary else Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            setSelectedService("2")
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedService === "2",
                        onClick = { setSelectedService("2") },
                        colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
                    )
                    Text(text = "Konselor (Lulusan S1 Psikologi)")
                }
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
                onClick = { navController.navigate(BaikanScreen.ChoosePackage.route) },
                enabled = selectedService != "",
            ) {
                Text(text = "Lanjut Pilih Paket")
            }
        }
    }
}

@Preview
@Composable
fun ChooseServicePreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface() {
            ChooseServiceScreen(navController)
        }
    }
}

