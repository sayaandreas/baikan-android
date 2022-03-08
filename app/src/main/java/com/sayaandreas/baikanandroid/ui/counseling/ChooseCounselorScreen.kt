package com.sayaandreas.baikanandroid.ui.counseling

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.sayaandreas.baikanandroid.model.Counselor
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.ui.main.MainViewModel
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import java.text.SimpleDateFormat
import java.util.*

@OptIn(com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun ChooseCounselorScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val list = mainViewModel.counselorList
    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text(text = "Pilih Konselor") }, navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        })
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(top = 24.dp)
        ) {
            items(items = list, itemContent = { item ->
                ChooseCounselorRow(item, onClick = {
                    mainViewModel.selectCounselor(item)
                    navController.navigate(BaikanScreen.CounselorDetail.route)
                })
            })
        }
    }
}

@Composable
fun ChooseCounselorRow(c: Counselor, onClick: () -> Unit) {
    val specialist = c.specialist.joinToString(separator = ", ", transform = {
        it.title
    })
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
            .height(140.dp)
            .clickable {
                onClick()
            }
    ) {
        AsyncImage(
            model = c.image,
            contentDescription = null,
            modifier = Modifier
                .width(110.dp)
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds
        )
        Column(
            Modifier
                .fillMaxHeight()
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(
                    text = c.fullName,
                    overflow = TextOverflow.Visible,
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "Spesialis di $specialist",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
            Column() {
                val fmt = SimpleDateFormat("EEEE, dd LLL HH:mm", Locale("ID"))
                Text(text = "Jadwal Tercepat", fontWeight = FontWeight.Medium, fontSize = 14.sp)
                Text(
                    text = fmt.format(c.schedule.first()),
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
    }
}


@Preview
@Composable
fun ChooseCounselorPreview() {
    val navController = rememberNavController()
    val mainViewModel = MainViewModel(null)
    BaikanAndroidTheme() {
        Surface {
            ChooseCounselorScreen(navController, mainViewModel)
        }
    }
}
