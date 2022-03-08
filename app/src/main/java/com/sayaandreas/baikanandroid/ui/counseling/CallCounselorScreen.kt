package com.sayaandreas.baikanandroid.ui.counseling

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.ui.main.MainViewModel
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class CounselingMethod() {
    Chat,
    Voice,
    Video
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CallCounselorScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    var (isWaiting, setIsWaiting) = rememberSaveable { mutableStateOf(true) }
    val (method, setMethod) = rememberSaveable { mutableStateOf(CounselingMethod.Chat) }

    var callTime by remember {
        mutableStateOf(1000L)
    }

    var isRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = callTime, key2 = isRunning) {
        if (isRunning) {
            delay(1000L)
            callTime += 1000L
        }
    }

    val closeSession = {
        isRunning = false
        navController.navigate(BaikanScreen.Home.route) {
            popUpTo(BaikanScreen.CallCounselor.route) {
                inclusive = true
            }
        }
    }

    val bgColor = when (method) {
        CounselingMethod.Chat -> Color.White
        else -> Color(50, 65, 90)
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(
                color = if (isWaiting) MaterialTheme.colors.primary else bgColor
            )
    ) {
        if (isWaiting) {
            Waiting(doneWaiting = { setIsWaiting(false) })
        } else {
            Crossfade(targetState = method) { m ->
                when (m) {
                    CounselingMethod.Chat -> Chatting(
                        closeSession,
                        setMethod,
                        startTimer = { isRunning = true }, mainViewModel
                    )
                    CounselingMethod.Voice -> Calling(
                        closeSession,
                        setMethod,
                        callTime,
                        mainViewModel
                    )
                    CounselingMethod.Video -> VideoCalling(
                        closeSession,
                        setMethod,
                        callTime,
                        mainViewModel
                    )
                }
            }

        }
    }
}


@Composable
fun Waiting(doneWaiting: () -> Unit) {
    var currentTime by remember {
        mutableStateOf(30L * 1000L)
    }

    var isRunning by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = currentTime, key2 = isRunning) {
        if (currentTime > 0 && isRunning) {
            delay(1000L)
            currentTime -= 1000L
        }
    }

    if (currentTime == 27000L) {
        isRunning = false
        doneWaiting()
    }


    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 24.dp),
            text = "Menghubungi Konselor",
            style = MaterialTheme.typography.h5,
            color = Color.White
        )
        Box(
            modifier = Modifier
                .border(
                    2.dp,
                    color = MaterialTheme.colors.primaryVariant,
                    shape = CircleShape
                )
                .padding(4.dp)
                .border(
                    3.dp,
                    color = MaterialTheme.colors.primaryVariant,
                    shape = CircleShape
                )
                .padding(6.dp)
                .clip(shape = CircleShape)
                .background(color = Color.White)
                .padding(24.dp)

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.size(90.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "${currentTime / 1000}")
                Text(text = "Detik")
            }
        }
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = "Mohon Menunggu",
            style = MaterialTheme.typography.h5,
            color = Color.White
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Anda akan segera tersambung",
            color = Color.White
        )
    }
}

@Composable
fun Calling(
    closeSession: () -> Unit,
    setMethod: (m: CounselingMethod) -> Unit,
    callTime: Long,
    mainViewModel: MainViewModel
) {
    val callTimeSec = callTime % 60000 / 1000;
    val callTimeMin = callTime / 60000;
    val callTimeSecFmt = if (callTimeSec > 9) callTimeSec else "0$callTimeSec"
    val callTimeMinFmt = if (callTimeMin > 9) callTimeMin else "0$callTimeMin"
    val img: Painter = painterResource(id = R.drawable.selfi_d)
    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = mainViewModel.selectedCounselor.value?.name ?: "",
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(bottom = 48.dp),
                text = "$callTimeMinFmt:$callTimeSecFmt",
                color = Color.White
            )
            AsyncImage(
                model = mainViewModel.selectedCounselor.value?.image ?: "",
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = CircleShape),
            )
        }

        Column(Modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        Modifier
                            .border(1.dp, color = Color.White, shape = CircleShape)
                            .padding(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_volume_mute_24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    IconButton(
                        onClick = { setMethod(CounselingMethod.Chat) },
                        Modifier
                            .border(1.dp, color = Color.White, shape = CircleShape)
                            .padding(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_message_24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    IconButton(
                        onClick = { setMethod(CounselingMethod.Video) },
                        Modifier
                            .border(1.dp, color = Color.White, shape = CircleShape)
                            .padding(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_videocam_24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        Modifier
                            .border(1.dp, color = Color.White, shape = CircleShape)
                            .padding(10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_speaker_phone_24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        closeSession()
                    },
                    Modifier
                        .clip(shape = CircleShape)
                        .background(color = Color.Red)
                        .padding(10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_call_end_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

        }

    }
}

@Composable
fun VideoCalling(
    closeSession: () -> Unit,
    setMethod: (m: CounselingMethod) -> Unit,
    callTime: Long,
    mainViewModel: MainViewModel
) {
    val img: Painter = painterResource(id = R.drawable.selfi_c)
    val callTimeSec = callTime % 60000 / 1000;
    val callTimeMin = callTime / 60000;
    val callTimeSecFmt = if (callTimeSec > 9) callTimeSec else "0$callTimeSec"
    val callTimeMinFmt = if (callTimeMin > 9) callTimeMin else "0$callTimeMin"
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(90.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = mainViewModel.selectedCounselor.value?.name ?: "",
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
            Text(
                text = "$callTimeMinFmt:$callTimeSecFmt",
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = mainViewModel.selectedCounselor.value?.image ?: "",
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
            Image(
                painter = painterResource(id = R.drawable.selfie),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 24.dp, bottom = 24.dp)
                    .border(2.dp, Color.White)
                    .width(110.dp)
                    .height(150.dp),
                contentScale = ContentScale.FillBounds
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                IconButton(
                    onClick = { setMethod(CounselingMethod.Chat) },
                    Modifier
                        .border(1.dp, color = Color.White, shape = CircleShape)
                        .padding(10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_message_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                IconButton(
                    onClick = {
                        closeSession()
                    },
                    Modifier
                        .background(color = Color.Red, shape = CircleShape)
                        .padding(10.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_call_end_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                IconButton(
                    onClick = { setMethod(CounselingMethod.Voice) },
                    Modifier
                        .border(1.dp, color = Color.White, shape = CircleShape)
                        .padding(10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_videocam_off_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Chatting(
    closeSession: () -> Unit,
    setMethod: (m: CounselingMethod) -> Unit,
    startTimer: () -> Unit,
    mainViewModel: MainViewModel
) {
    val (showAlert, setShowAlert) = rememberSaveable { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Row(
            Modifier
                .background(color = MaterialTheme.colors.primary)
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxHeight()
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable { setShowAlert(true) }
                )
                AsyncImage(
                    model = mainViewModel.selectedCounselor.value?.image ?: "",
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clip(shape = CircleShape)
                        .size(36.dp)
                )
                Text(
                    text = mainViewModel.selectedCounselor.value?.name ?: "",
                    color = Color.White,
                    style = MaterialTheme.typography.subtitle1
                )
            }

            Row() {
                Icon(
                    Icons.Default.Phone,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        setMethod(CounselingMethod.Voice)
                        startTimer()
                    }
                )
                Icon(
                    painterResource(id = R.drawable.ic_baseline_videocam_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable {
                            setMethod(CounselingMethod.Video)
                            startTimer()
                        }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth(2 / 3f)
                    .background(color = Color(235, 235, 235))
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(text = "Halo Johny", color = Color.Black)
                Text(
                    text = "09.00",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.align(
                        Alignment.BottomEnd
                    )
                )
            }
            Box(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth(2 / 3f)
                    .background(color = Color(235, 235, 235))
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(text = "Apa kabar?", color = Color.Black)
                Text(
                    text = "09.00",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.align(
                        Alignment.BottomEnd
                    )
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp, end = 16.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth(2 / 3f)
                    .background(color = MaterialTheme.colors.primaryVariant)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(text = "Hai", color = Color.Black)
                Text(
                    text = "09.01",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.align(
                        Alignment.BottomEnd
                    )
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp, end = 16.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .fillMaxWidth(2 / 3f)
                    .background(color = MaterialTheme.colors.primaryVariant)
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(text = "Boleh call sekarang?", color = Color.Black)
                Text(
                    text = "09.01",
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.align(
                        Alignment.BottomEnd
                    )
                )
            }
        }
        Divider()
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = CircleShape
            )
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 16.dp)) {
                Icon(Icons.Default.Send, contentDescription = null)
            }
        }

        if (showAlert) {
            AlertDialog(
                onDismissRequest = { setShowAlert(false) },
                confirmButton = {
                    Button(onClick = { closeSession() }) {
                        Text(text = "Ya, tutup")
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = { setShowAlert(false) }) {
                        Text(text = "Batal")
                    }
                },
                title = { Text(text = "Tutup Sesi") },
                text = { Text(text = "Apakah kamu yakin ingin menutup sesi ini ?") })
        }
    }
}

@Preview
@Composable
fun CallCounselorScreenPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface {
            CallCounselorScreen(navController, MainViewModel(null))
        }
    }
}

