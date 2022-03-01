package com.sayaandreas.baikanandroid.ui.counseling

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.sayaandreas.baikanandroid.BaikanScreen
import com.sayaandreas.baikanandroid.R

data class CounselorDetail(val name: String, val specialist: List<String>, val avatar: Int)

@OptIn(com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun ChooseCounselorScreen(navController: NavHostController) {
    val counselorList = listOf(
        CounselorDetail(
            "Evan Tanuwijaya",
            specialist = listOf("Sosial", "Kecemasan", "Kesepian", "Emosi"),
            avatar = R.drawable.counselor1
        ),
        CounselorDetail(
            "Joshua Simorangkir",
            specialist = listOf("Kecemasan", "Kesepian", "Pekerjaab", "Emosi"),
            avatar = R.drawable.counselor2
        ),
        CounselorDetail(
            "Anastasya Febriana",
            specialist = listOf("Kesepian", "Kecemasa", "Pekerjaab", "Emosi"),
            avatar = R.drawable.counselor3
        ),
        CounselorDetail(
            "Victoria Singh",
            specialist = listOf("Kesepian", "Pekerjaab", "Emosi", "Kecemasan"),
            avatar = R.drawable.counselor4
        )
    )
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
        Column(Modifier.padding(24.dp)) {
            val pagerState = rememberPagerState()
            HorizontalPager(
                count = counselorList.size,
                state = pagerState,
                // Add 32.dp horizontal padding to 'center' the pages
                contentPadding = PaddingValues(horizontal = 8.dp),
                // Add some horizontal spacing between items
                itemSpacing = 16.dp,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) { page ->
                PagerSampleItem(
                    data = counselorList[page],
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2 / 3f)
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Button(
                    onClick = {
                        navController.navigate(BaikanScreen.CounselingAgreementScreen.name)
                    },
                    shape = CircleShape,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Pilih Konselor")
                }
            }
        }
    }
}

@Composable
internal fun PagerSampleItem(
    data: CounselorDetail,
    modifier: Modifier = Modifier,
) {
    val image: Painter = painterResource(id = data.avatar)
    Card(modifier, elevation = 4.dp) {
        Column(Modifier.fillMaxSize()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = image,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(64.dp)
                )
                Column {
                    Text(text = data.name, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                    Text(text = "SIPP: 123XXXX")
                }
            }
            Divider()
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = null,
                        Modifier.size(24.dp)
                    )
                    Text(
                        text = "Sudah membantu 200+ Pembicara",
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_scroll),
                        contentDescription = null,
                        Modifier.size(24.dp)
                    )
                    Text(text = "Expertise", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                }
                FlowRow(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    mainAxisSpacing = 8.dp,
                    crossAxisSpacing = 8.dp
                ) {
                    data.specialist.forEach {
                        TagText(it)
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_star_rate_24),
                        contentDescription = null,
                        Modifier.size(24.dp)
                    )
                    Text(text = "Review", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                }
                Column(
                    Modifier.padding(top = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "N - 24 tahun")
                    Text(text = "Saya mendapatkan jawaban setelah konseling sama konselor")
                }
            }
        }
    }
}

@Composable
fun TagText(text: String) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50))
            .background(color = MaterialTheme.colors.primary)
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onPrimary,
            fontSize = 13.sp
        )
    }
}

@Preview
@Composable
fun ChooseCounselorPreview() {
    val navController = rememberNavController()
    MaterialTheme() {
        Surface {
            ChooseCounselorScreen(navController)
        }
    }
}
