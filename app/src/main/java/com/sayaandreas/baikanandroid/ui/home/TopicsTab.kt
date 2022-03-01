package com.sayaandreas.baikanandroid.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.R

data class Topic(val title: String, val icon: Int)

@Composable
fun TopicsTab(navController: NavHostController) {
    var (selectedTopic, setSelectedTopic) = rememberSaveable { mutableStateOf("") }
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
                            navController.navigate(BaikanScreen.TopicIntro.route)
                        },
                        Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Text(text = "Mulai Konseling")
                    }
                }
            }
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
                        Image(
                            painter = image,
                            contentDescription = "",
                            Modifier
                                .size(32.dp)
                                .padding(bottom = 8.dp)
                        )
                        Text(text = topicTitle, style = MaterialTheme.typography.caption)
                    }
                }
            }
        }
    )
}