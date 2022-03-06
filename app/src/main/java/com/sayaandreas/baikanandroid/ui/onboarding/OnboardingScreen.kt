package com.sayaandreas.baikanandroid.ui.onboarding

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.model.ImageDataLocal
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

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
//    val pagerState = rememberPagerState()


    val slideList = listOf(
        ImageDataLocal(
            "Vitamin",
            R.drawable.onboarding_1,
        ),
        ImageDataLocal(
            "Obat",
            R.drawable.onboarding_3
        ),
        ImageDataLocal(
            "Antibiotik",
            R.drawable.onboarding_4
        ),
    )

    val pagerState = rememberPagerState(
        slideList.size,
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2500)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primaryVariant)
    ) {
        Column(Modifier.fillMaxSize().padding(start = 24.dp, end = 24.dp)) {
            Column(Modifier.weight(1f)) {
                HorizontalPager(
                    count = slideList.size,
                    state = pagerState,
                    // Add 32.dp horizontal padding to 'center' the pages
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    // Add some horizontal spacing between items
                    itemSpacing = 16.dp,
                    modifier = Modifier
                        .weight(1f),
                    verticalAlignment = Alignment.Top,
                ) { page ->
                    PagerSampleItem(
                        data = slideList[page],
                        modifier = Modifier
                            .fillMaxWidth().weight(1f),
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
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(BaikanScreen.Register.route) {
                            popUpTo(BaikanScreen.Onboarding.route) {
                                inclusive = true
                            }
                        }
                    },
                    shape = CircleShape,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Daftar dulu, yuk!")
                }

                Button(
                    onClick = {
                        navController.navigate(BaikanScreen.Home.route) {
                            popUpTo(BaikanScreen.Onboarding.route) {
                                inclusive = true
                            }
                        }
                    },
                    shape = CircleShape,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Telusuri")
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Sudah punya akun? ")
                    Text(
                        text = "Masuk", modifier = Modifier
                            .clickable {
                                navController.navigate(BaikanScreen.Login.route) {
                                    popUpTo(BaikanScreen.Onboarding.route) {
                                        inclusive = true
                                    }
                                }
                            }, color = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}

@Composable
internal fun PagerSampleItem(
    data: ImageDataLocal,
    modifier: Modifier = Modifier,
    description: @Composable () -> Unit
) {
    val image: Painter = painterResource(id = data.resId)
    Column(modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(4 / 5f)
            )
        }
        Text(
            text = data.descciption,
            color = MaterialTheme.colors.primary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp, top = 16.dp),
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