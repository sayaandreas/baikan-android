package com.sayaandreas.baikanandroid.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.navigation.NavHostController
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.model.Counselor
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Topic(val title: String, val icon: Int)

@Composable
fun TopicsTab(
    navController: NavHostController,
    quick: Boolean = false,
    counselorList: List<Counselor>
) {
    var (selectedTopic, setSelectedTopic) = rememberSaveable { mutableStateOf("") }
    var (showDialog, setShowDialog) = rememberSaveable { mutableStateOf(false) }
    val counselingTopics = listOf(
        Topic(title = "Pekerjaan", icon = R.drawable.briefcase),
        Topic(title = "Keluarga", icon = R.drawable.family),
        Topic(title = "Pasangan", icon = R.drawable.heart),
        Topic(title = "Emosi", icon = R.drawable.poker_face),
        Topic(title = "Pendidikan", icon = R.drawable.mortarboard),
        Topic(title = "Kesepian", icon = R.drawable.alone),
        Topic(title = "Sosial", icon = R.drawable.social_media),
        Topic(title = "Kecanduan", icon = R.drawable.brain),
        Topic(title = "Lainnya", icon = R.drawable.more)
    )
    Column(
        Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = { Text(text = "Konseling") })
        Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
            Text(text = "Topik Konseling", style = MaterialTheme.typography.h6)
            Text(text = "Pilih topik konseling yang kamu butuhkan")
            CounselingTopics(counselingTopics, selectedTopic, setSelectedTopic)

            if (selectedTopic != "") {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                ) {
                    Button(
                        onClick = {
                            if (quick) {
                                setShowDialog(true)
                            } else {
                                navController.navigate(BaikanScreen.TopicIntro.route)
                            }
                        },
                        Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Text(text = "Mulai Konseling")
                    }
                }
            }
        }

        if (showDialog) {
            SelectCounselorDialog(
                onDismissRequest = { setShowDialog(false) },
                onSuccess = {
                    setShowDialog(false)
                    navController.navigate(BaikanScreen.CallCounselor.route) {
                        popUpTo(BaikanScreen.Home.route) {
                            inclusive = true
                        }
                    }
                }, counselorList = counselorList
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun CounselingTopics(
    topics: List<Topic>,
    selectedTopic: String,
    setSelectedTopic: (t: String) -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),

        contentPadding = PaddingValues(
            top = 24.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(topics.size) { index ->
                val topicTitle = topics[index].title
                val image: Painter = painterResource(id = topics[index].icon)
                val isSelected = selectedTopic === topicTitle
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    backgroundColor = if (isSelected) MaterialTheme.colors.primary else Color.White,
                    elevation = 4.dp,
                    onClick = {
                        if (isSelected) {
                            setSelectedTopic("")
                        } else {
                            setSelectedTopic(topicTitle)
                        }
                    }
                ) {
                    Column(
                        Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = image,
                            contentDescription = "",
                            Modifier
                                .size(32.dp)
                                .padding(bottom = 8.dp),
                            tint = if (isSelected) Color.White else Color.Black
                        )
                        Text(text = topicTitle, style = MaterialTheme.typography.caption)
                    }
                }
            }
        }
    )
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SelectCounselorDialog(
    onDismissRequest: () -> Unit,
    onSuccess: () -> Unit,
    counselorList: List<Counselor>
) {
    val (isLoading, setIsLoading) = remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    scope.launch {
        delay(1500L)
        setIsLoading(false)
    }

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            securePolicy = SecureFlagPolicy.Inherit
        )
    ) {
        if (isLoading) {
            Column(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(modifier = Modifier.padding(bottom = 8.dp))
                Text(text = "Mencari konselor yang tersedia")
            }
            return@Dialog
        }
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Pilih Konselor", style = MaterialTheme.typography.subtitle1)
                }
                Divider(Modifier.padding(top = 24.dp, bottom = 8.dp))
                counselorList.forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSuccess()
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = SpaceBetween,
                    ) {
                        Row(
                            Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(id = it.image),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(shape = CircleShape)
                                    .padding(end = 8.dp),
                            )
                            Column() {
                                Text(text = it.name)
                                Text(
                                    text = "${it.specialist[0].title}, ${it.specialist[0].title}",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)
                        )
                    }
                    Divider(Modifier.padding(top = 8.dp, bottom = 8.dp))
                }
            }
        }
    }
}