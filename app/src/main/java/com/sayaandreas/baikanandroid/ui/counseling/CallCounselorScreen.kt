package com.sayaandreas.baikanandroid.ui.counseling

import android.annotation.SuppressLint
import android.graphics.Color.rgb
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CallCounselorScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    var (isWaiting, setIsWaiting) = rememberSaveable { mutableStateOf(true) }

    scope.launch {
        delay(4000)
        setIsWaiting(false)
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(
                color = if (isWaiting) MaterialTheme.colors.primary else Color(
                    rgb(
                        14,
                        35,
                        46
                    )
                )
            )
    ) {
        Column(Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
            if (isWaiting) {
                Waiting()
            } else {
                Calling()
            }
        }
    }
}


@Composable
fun Waiting() {
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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "1")
                Text(text = "Menit")
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
fun Calling() {
    val img: Painter = painterResource(id = R.drawable.counselor1)
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "Joshua Simorangkir",
            style = MaterialTheme.typography.h5,
            color = Color.White
        )
        Text(
            modifier = Modifier.padding(bottom = 48.dp),
            text = "00:30",
            color = Color.White
        )
        Image(
            painter = img,
            contentDescription = null,
            Modifier
                .size(120.dp)
                .clip(shape = CircleShape)
        )
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
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_volume_mute_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Text(text = "Mute", modifier = Modifier.padding(top = 8.dp), color = Color.White)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    Modifier
                        .border(1.dp, color = Color.White, shape = CircleShape)
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_message_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Text(text = "Message", modifier = Modifier.padding(top = 8.dp), color = Color.White)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    onClick = { /*TODO*/ },
                    Modifier
                        .border(1.dp, color = Color.White, shape = CircleShape)
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_speaker_phone_24),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Text(text = "Speaker", modifier = Modifier.padding(top = 8.dp), color = Color.White)
            }
        }

        Row(Modifier.padding(top = 32.dp)) {
            IconButton(
                onClick = { /*TODO*/ },
                Modifier
                    .clip(shape = CircleShape)
                    .background(color = Color.Red)
                    .padding(16.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_call_end_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun CallCounselorScreenPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface {
            CallCounselorScreen(navController)
        }
    }
}

