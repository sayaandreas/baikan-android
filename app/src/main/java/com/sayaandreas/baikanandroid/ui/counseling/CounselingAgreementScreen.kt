package com.sayaandreas.baikanandroid.ui.counseling

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme

@Composable
fun CounselingAgreementScreen(navController: NavHostController) {
    val checkedState = remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text(text = "Form Persetujuan") }, navigationIcon = {
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
                .padding(start = 16.dp, end = 16.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Text(
                text = "Dengan ini anda menyatakan:",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 24.dp)
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                        colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
                    )
                    Text(text = "Berusia diatas 17 tahun")
                }
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                        colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
                    )
                    Text(text = "Saat ini tidak sedang menyakiti atau membahayakan deiri sendiri (self-harm/suicidal attempts)*")
                }
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                        colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
                    )
                    Text(text = "Memahami bahwa layanan konseling yang diikuti terlingkup ke dalam beberapa kegiatan, yaitu assessment, konseling, pemberian terapi serta penegakan diagnosis jika diperlukan")
                }
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                        colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
                    )
                    Text(text = "Bersedia mengikuti layanan konseling tanpa adanya paksaan dari pihak manapun")
                }
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                        colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
                    )
                    Text(text = "Telah membaca serta menyetujui syarat dan ketentuan berlaku di bawah")
                }
            }
            TextButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(top = 32.dp)) {
                Text(text = "Syarat dan ketentuan berlaku")
            }
            Row(
                Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .background(color = Color(255, 232, 169))
                    .border(
                        width = 1.dp,
                        color = Color(255, 232, 169),
                        shape = MaterialTheme.shapes.large
                    )
                    .padding(16.dp)
            ) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Jika kamu sedang melakukan tindakan pada poin bertanda bintang di atas, sebaiknya kamu segera menghubungi 119 ext. 8 untuk dapat pertolongan darurat.")
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 24.dp, end = 24.dp)) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                onClick = { navController.navigate(BaikanScreen.OrderDetail.route) },
                enabled = checkedState.value == true
            ) {
                Text(text = "Selanjutnya")
            }
        }
    }
}

@Preview
@Composable
fun CounselingAgreementPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface {
            CounselingAgreementScreen(navController)
        }
    }
}