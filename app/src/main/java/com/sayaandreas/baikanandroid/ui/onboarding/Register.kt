package com.sayaandreas.baikanandroid.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.BaikanScreen
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme

@Composable
fun RegisterScreen(navController: NavHostController) {
    val image: Painter = painterResource(id = R.drawable.shield)
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)) {
            Text(text = "Daftar akun dulu, yuk!", style = MaterialTheme.typography.h5)
            OutlinedTextField(
                value = "",
                placeholder = { Text(text = "Name lengkap kamu") },
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                shape = RoundedCornerShape(50),
            )
            OutlinedTextField(
                value = "",
                placeholder = { Text(text = "Email") },
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                shape = RoundedCornerShape(50),
            )
            OutlinedTextField(
                value = "",
                placeholder = { Text(text = "Kata sandi") },
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(50),
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(BaikanScreen.WelcomeScreen.name)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(50),

                    ) {
                    Text(text = "Daftar")
                }
                Box(modifier = Modifier.padding(vertical = 16.dp)) {
                    Spacer(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )
                    Text(
                        text = "atau",
                        color = Color.LightGray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(MaterialTheme.colors.background)
                            .padding(horizontal = 16.dp)
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                ) {
                    Text(text = "Daftar cepat dengan Google")
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

            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.large)
                    .background(color = Color(255, 232, 169))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(painter = image, contentDescription = null)
                Text(text = "Data kamu dijamin aman 100%, hanya digunakan untuk keperluan konseling, dan tidak akan disebarluaskan diluar aplikasi BAIK-AN.")
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    BaikanAndroidTheme() {
        Surface() {
            RegisterScreen(navController)
        }
    }
}