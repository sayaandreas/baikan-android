package com.sayaandreas.baikanandroid.ui.onboarding.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sayaandreas.baikanandroid.R

@Composable
fun RegisterLayout(
    title: String,
    imageRes: Int,
    enableNext: Boolean,
    onBackPressed: (() -> Unit)? = null,
    onSkipPressed: () -> Unit,
    onNextPressed: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(400.dp),
                content = {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            horizontalArrangement = if (onBackPressed != null) Arrangement.SpaceBetween else Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (onBackPressed != null) {
                                IconButton(onClick = onBackPressed) {
                                    Icon(
                                        Icons.Default.ArrowBack,
                                        contentDescription = null,
                                    )
                                }
                            }
                            TextButton(onClick = onSkipPressed) {
                                Text(text = "Skip", color = Color.Black)
                            }
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                elevation = 0.dp,
                contentColor = Color.Black
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(top = 24.dp, start = 24.dp, end = 24.dp)
        ) {
            Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
            ) {
                content()
            }
            Row(Modifier.padding(top = 48.dp)) {
                Button(
                    onClick = { onNextPressed() },
                    Modifier.fillMaxWidth(),
                    shape = CircleShape,
                    enabled = enableNext
                ) {
                    Text(text = "Selanjutnya")
                }
            }
        }
    }
}