package com.sayaandreas.baikanandroid.ui.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.sayaandreas.baikanandroid.R
import com.sayaandreas.baikanandroid.model.User
import com.sayaandreas.baikanandroid.ui.main.BaikanScreen
import com.sayaandreas.baikanandroid.ui.main.MainViewModel

@Composable
fun RegisterScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val image: Painter = painterResource(id = R.drawable.shield)
    val registerViewModel: RegisterViewModel = viewModel()

    val isLoading: Boolean by registerViewModel.isLoading
    val user: User? by registerViewModel.user
    val users: List<User> = registerViewModel.users
    val name: String by registerViewModel.name
    val nameTouched: Boolean by registerViewModel.nameTouched
    val nameError: Boolean by registerViewModel.nameError
    val email: String by registerViewModel.email
    val emailTouched: Boolean by registerViewModel.emailTouched
    val emailError: Boolean by registerViewModel.emailError
    val password: String by registerViewModel.password
    val passwordTouched: Boolean by registerViewModel.passwordTouched
    val passwordError: Boolean by registerViewModel.passwordError
    val showGoogleAuth: Boolean by registerViewModel.showGoogleAuth
    val isRegistered: Boolean by registerViewModel.isRegistered

    val enableRegisterButton =
        name.isNotBlank() && email.isNotBlank() && password.isNotBlank()

    if (isLoading && user != null && mainViewModel.user.value == null) {
        user?.let {
            mainViewModel.setCurrentUser(it)
            registerViewModel.doneLoading()
        }
    }

    if (!isLoading && !showGoogleAuth && !isRegistered && mainViewModel.user.value != null) {
        navController.navigate(BaikanScreen.Welcome.route) {
            popUpTo(BaikanScreen.Onboarding.route) {
                inclusive = true
            }
        }
        registerViewModel.doneRegister()
    }


    Column(
        Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp)) {
            Text(text = "Datfar akun dulu, yuk!", style = MaterialTheme.typography.h5)

            OutlinedTextField(
                value = name,
                placeholder = { Text(text = "Nama lengkap kamu") },
                onValueChange = {
                    registerViewModel.setName(it)
                    if (it.isBlank()) registerViewModel.setNameError(true) else registerViewModel.setNameError(
                        false
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .onFocusChanged {
                        if (it.hasFocus) {
                            registerViewModel.setNameTouched(true)
                        } else {
                            if (nameTouched && name.isBlank())
                                registerViewModel.setNameError(true)
                        }
                    },
                shape = RoundedCornerShape(50),
                isError = nameError
            )
            if (nameError) {
                Text(
                    text = "Name is required",
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                )
            }

            OutlinedTextField(
                value = email,
                placeholder = { Text(text = "Email") },
                onValueChange = {
                    registerViewModel.setEmail(it)
                    if (it.isBlank()) registerViewModel.setEmailError(true) else registerViewModel.setEmailError(
                        false
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .onFocusChanged {
                        if (it.hasFocus) {
                            registerViewModel.setEmailTouched(true)
                        } else {
                            if (emailTouched && email.isBlank())
                                registerViewModel.setEmailError(true)
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
                value = password,
                placeholder = { Text(text = "Kata sandi") },
                onValueChange = {
                    registerViewModel.setPassword(it)
                    if (it.isBlank()) registerViewModel.setPasswordError(true) else registerViewModel.setPasswordError(
                        false
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .onFocusChanged {
                        if (it.hasFocus) {
                            registerViewModel.setPasswordTouched(true)
                        } else {
                            if (passwordTouched && password.isBlank())
                                registerViewModel.setPasswordError(true)
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
                        registerViewModel.registerUser(User(name = "Johny Pramono", email = email))
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    enabled = enableRegisterButton,

                    ) {
                    if (isLoading && !showGoogleAuth) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            color = Color.White
                        )
                    } else {
                        Text(text = "Datfar")
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
                    onClick = { registerViewModel.setShowGoogleAuth(true) },
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
                        Text(text = "Datfar cepat dengan Google")
                    }
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp), horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Sudah punya akun? ")
                    Text(
                        text = "Masuk", modifier = Modifier
                            .clickable {
                                navController.navigate(BaikanScreen.Register.route) {
                                    popUpTo(BaikanScreen.Onboarding.route) {
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
                onDismissRequest = { registerViewModel.setShowGoogleAuth(false) },
                onSuccess = {
                    registerViewModel.registerUser(it)
                })

        }
    }
}
