package com.sayaandreas.baikanandroid.ui.onboarding.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.model.User
import com.sayaandreas.baikanandroid.ui.main.MainViewModel
import com.sayaandreas.baikanandroid.ui.onboarding.GoogleAuthDialog

@Composable
fun LoginScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val image: Painter = painterResource(id = R.drawable.shield)
    val loginViewModel: LoginViewModel = viewModel()

    val isLoading: Boolean by loginViewModel.isLoading
    val user: User? by loginViewModel.user
    val users: List<User> = loginViewModel.users
    val email: String by loginViewModel.email
    val password: String by loginViewModel.password
    val passwordTouched: Boolean by loginViewModel.passwordTouched
    val passwordError: Boolean by loginViewModel.passwordError
    val emailTouched: Boolean by loginViewModel.emailTouched
    val emailError: Boolean by loginViewModel.emailError
    val showGoogleAuth: Boolean by loginViewModel.showGoogleAuth
    val isLoggedIn: Boolean by loginViewModel.isLoggedIn

    val enableLoginButton =
        email.isNotBlank() && password.isNotBlank()

    if (isLoading && user != null && mainViewModel.currentUser.value == null) {
        user?.let {
            mainViewModel.setCurrentUser(it)
            loginViewModel.doneLoading()
        }
    }

    if (!isLoading && !showGoogleAuth && !isLoggedIn && mainViewModel.currentUser.value != null) {
        navController.navigate(BaikanScreen.Home.route) {
            popUpTo(BaikanScreen.Login.route) {
                inclusive = true
            }
        }
        loginViewModel.doneLoggedIn()
    }


    Column(
        Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)) {
            Text(text = "Masuk akun dulu, yuk!", style = MaterialTheme.typography.h5)

            OutlinedTextField(
                singleLine = true,
                value = email,
                placeholder = { Text(text = "Email", style = MaterialTheme.typography.body2) },
                onValueChange = {
                    loginViewModel.setEmail(it)
                    if (it.isBlank()) loginViewModel.setEmailError(true) else loginViewModel.setEmailError(
                        false
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .onFocusChanged {
                        if (it.hasFocus) {
                            loginViewModel.setEmailTouched(true)
                        } else {
                            if (emailTouched && email.isBlank())
                                loginViewModel.setEmailError(true)
                        }
                    },
                shape = RoundedCornerShape(50),
                isError = emailError
            )
            if (emailError) {
                Text(
                    text = "Email is required",
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                )
            }
            OutlinedTextField(
                singleLine = true,
                value = password,
                placeholder = { Text(text = "Kata sandi", style = MaterialTheme.typography.body2) },
                onValueChange = {
                    loginViewModel.setPassword(it)
                    if (it.isBlank()) loginViewModel.setPasswordError(true) else loginViewModel.setPasswordError(
                        false
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .onFocusChanged {
                        if (it.hasFocus) {
                            loginViewModel.setPasswordTouched(true)
                        } else {
                            if (passwordTouched && password.isBlank())
                                loginViewModel.setPasswordError(true)
                        }
                    },
                shape = RoundedCornerShape(50),
            )
            if (passwordError) {
                Text(
                    text = "Password is required",
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                )
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp)
            ) {
                Button(
                    onClick = {
                        loginViewModel.loginUser(User(name = "Johny Pramono", email = email))
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    enabled = enableLoginButton,

                    ) {
                    if (isLoading && !showGoogleAuth) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            color = Color.White
                        )
                    } else {
                        Text(text = "Login")
                    }
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
                OutlinedButton(
                    onClick = { loginViewModel.setShowGoogleAuth(true) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, color = MaterialTheme.colors.primary)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_google__g__logo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)
                                .padding(end = 8.dp)
                        )
                        Text(text = "Masuk cepat dengan Google")
                    }
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp), horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Belum punya akun? ")
                    Text(
                        text = "Daftar", modifier = Modifier
                            .clickable {
                                navController.navigate(BaikanScreen.Register.route) {
                                    popUpTo(BaikanScreen.Login.route) {
                                        inclusive = true
                                    }
                                }
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

        if (showGoogleAuth) {
            GoogleAuthDialog(
                isLoading,
                users,
                onDismissRequest = { loginViewModel.setShowGoogleAuth(false) },
                onSuccess = {
                    loginViewModel.loginUser(it)
                })

        }
    }
}
