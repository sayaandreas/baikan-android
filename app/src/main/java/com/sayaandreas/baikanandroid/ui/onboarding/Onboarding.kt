package com.sayaandreas.baikanandroid.ui.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.sayaandreas.baikanandroid.BaikanScreen
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.counseling.CounselorDetail
import com.sayaandreas.baikanandroid.ui.counseling.PagerSampleItem
import com.sayaandreas.baikanandroid.ui.counseling.TagText
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

data class Slide(val title: String, val img: Int)

@Composable
fun Desc1() {
    Text(
        buildAnnotatedString {
            append("Disini kamu bisa nemuin banyak konten psikologi yang dijamin bagus banget buat menjadi ")

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            ) {
                append("vitamin ")
            }
            append("untuk kesehatan mentalmu!")
        }
    )
}

@Composable
fun Desc2() {
    Text(
        buildAnnotatedString {
            append("Lalu kamu juga bisa melakukan journaling & meditasi yang bisa menjadi ")

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            ) {
                append("obat ")
            }
            append("untuk membantumu mengelola emosi dan pikiranmu lebih baik lagi")
        }
    )
}

@Composable
fun Desc3() {
    Text(
        buildAnnotatedString {
            append("Terakhir, kami juga menyediakan ")

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            ) {
                append("antibiotik ")
            }
            append("berupa layanan konseling psikologis profesional ")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("24 JAM ")
            }
            append("dengan harga yang pastinya terjangkau \uD83D\uDE0A")
        }
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()

    val slideList = listOf(
        Slide(
            "Vitamin",
            R.drawable.onboarding_1,
        ),
        Slide(
            "Obat",
            R.drawable.onboarding_3
        ),
        Slide(
            "Antibiotik",
            R.drawable.onboarding_4
        ),
    )
    Column(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primaryVariant)
    ) {
        Column(Modifier.padding(start = 24.dp, end = 24.dp)) {
            Column {
                HorizontalPager(
                    count = slideList.size,
                    state = pagerState,
                    // Add 32.dp horizontal padding to 'center' the pages
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    // Add some horizontal spacing between items
                    itemSpacing = 16.dp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) { page ->
                    PagerSampleItem(
                        data = slideList[page],
                        modifier = Modifier
                            .fillMaxWidth(),
                        description = {
                            when (page) {
                                0 -> {
                                    Desc1()
                                }
                                1 -> {
                                    Desc2()
                                }
                                2 -> {
                                    Desc3()
                                }
                            }
                        }
                    )
                }

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                )

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp)
                ) {
                    Button(
                        onClick = {
                            navController.navigate(BaikanScreen.RegisterScreen.name)
                        },
                        shape = CircleShape,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Daftar dulu, yuk!")
                    }

                    Button(
                        onClick = {
                            navController.navigate(BaikanScreen.Home.name)
                        },
                        shape = CircleShape,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Telusuri")
                    }

                    Row(Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.Center) {
                        Text(text = "Sudah punya akun? ")
                        Text(
                            text = "Masuk", modifier = Modifier
                                .clickable {
                                    navController.navigate(BaikanScreen.LoginScreen.name)
                                }, color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun PagerSampleItem(
    data: Slide,
    modifier: Modifier = Modifier,
    description: @Composable () -> Unit
) {
    val image: Painter = painterResource(id = data.img)
    Column(modifier) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(400.dp)
                    .aspectRatio(2 / 3f)
            )
        }
        Text(
            text = data.title,
            color = MaterialTheme.colors.primary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            textAlign = TextAlign.Center
        )
        description()
    }

}

@Preview
@Composable
fun OnboardingScreenPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface() {
            OnboardingScreen(navController)
        }
    }
}