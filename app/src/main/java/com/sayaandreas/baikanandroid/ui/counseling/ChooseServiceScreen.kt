package com.sayaandreas.baikanandroid.ui.counseling

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.R

@Composable
fun ChooseServiceScreen(navController: NavHostController) {
    var (selectedService, setSelectedService) = rememberSaveable { mutableStateOf("") }
    val image: Painter = painterResource(id = R.drawable.doctors)
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
        Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
            Image(
                painter = image,
                contentDescription = "",
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(1 / 1f)
                    .padding(bottom = 24.dp)
            )
            Row(
                Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
                Text(
                    fontSize = 12.sp,
                    text = "Telah mendapat izin konseling dari HIMPSI"
                )
            }
            Row(
                Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
                Text(
                    fontSize = 12.sp,
                    text = "Direkomendasikan untuk kamu yang memiliki masalah cukup berat hingga rumit"
                )
            }
            Row(
                Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
                Text(
                    fontSize = 12.sp,
                    text = "Direkomendasikan untuk kamu yang memiliki masalah cukup berat hingga rumit"
                )
            }
            Column(Modifier.padding(top = 24.dp, end = 16.dp, start = 16.dp)) {
                Text(text = "Pilih Jenis Layanan", style = MaterialTheme.typography.subtitle1)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    RadioButton(
                        selected = selectedService === "1",
                        onClick = { setSelectedService("1") })
                    Text(text = "Psikolog (Lulusan S2 Psikologi)")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedService === "2",
                        onClick = { setSelectedService("2") })
                    Text(text = "Konselor (Lulusan S1 Psikologi)")
                }


            }
            Row(Modifier.padding(vertical = 24.dp)) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    onClick = { navController.navigate(BaikanScreen.ChoosePackage.route) }) {
                    Text(text = "Lanjut Pilih Paket")
                }
            }
        }
    }
}

