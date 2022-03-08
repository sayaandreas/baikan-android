package com.sayaandreas.baikanandroid.ui.counseling

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.sayaandreas.baikanandroid.model.Testimony
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.ui.main.MainViewModel
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@Composable
fun CounselorDetailScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val counselor = mainViewModel.selectedCounselor.value
    val specialistText = counselor?.specialist?.joinToString(separator = ", ", transform = {
        it.title
    })

    if (counselor == null) {
        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            TopAppBar(
                title = { Text(text = counselor.name) },
                navigationIcon = {
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
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = counselor.image,
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .padding(bottom = 8.dp)
                )
                CounselorIntro(counselor.fullName, specialistText ?: "", counselor.patients)
                Divider(Modifier.padding(vertical = 24.dp))
                CounselorProfile(counselor.firstName, counselor.motto, counselor.description)
                Divider(Modifier.padding(vertical = 24.dp))
                CounselorTestimonies(counselor.testimonies)
            }
            Row(
                Modifier
                    .padding(bottom = 24.dp, start = 24.dp, end = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = {
                        navController.navigate(BaikanScreen.CounselingAgreement.route)
                    },
                    shape = CircleShape,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Konseling dengan ${counselor.firstName}")
                }
            }
        }
    }
}

@Composable
fun CounselorIntro(fullName: String, specialistText: String, patients: Int) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 24.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = fullName,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = "Spesialis di $specialistText",
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 16.dp),
            fontSize = 14.sp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.Person,
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
            Text(
                text = "Sudah membantu +$patients klien",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

    }
}

@Composable
fun CounselorProfile(firstName: String, motto: String, description: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
    ) {
        Text(
            text = "Profil Konselor $firstName",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.subtitle1,
        )
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "\"$motto\"",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = description,
            color = MaterialTheme.colors.onBackground,
            fontSize = 14.sp
        )
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun CounselorTestimonies(list: List<Testimony>) {
    val lazyListState = rememberLazyListState()
    Text(
        text = "Cerita mereka yang sudah konseling",
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(start = 24.dp, end = 24.dp)
    )
    LazyRow(
        state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(
            lazyListState = lazyListState,
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 32.dp, start = 24.dp, end = 24.dp)
    ) {
        items(items = list, itemContent = { item ->
            Column(
                Modifier
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.primaryVariant)
                    .width(300.dp)
                    .padding(16.dp)
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colors.primary,
                                fontSize = 16.sp
                            )
                        ) {
                            append("${item.title}\n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontSize = 14.sp
                            )
                        ) {
                            append(item.description)
                        }

                    },
                    maxLines = 5,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.primary)
                        .size(32.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.name.toString(),
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        })
    }

}

@Preview
@Composable
fun CounselorDetailPreview() {
    val navController = rememberNavController()
    val mainViewModel = MainViewModel(sharedPref = null)
    BaikanAndroidTheme() {
        Surface() {
            CounselorDetailScreen(navController = navController, mainViewModel)
        }
    }
}