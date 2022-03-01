package com.sayaandreas.baikanandroid.ui.onboarding

import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayaandreas.baikanandroid.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val users = listOf(
        User("Johny Pramono", "johnyisworking@gmail.com"),
        User("Johny Pramono", "johnypersonal@gmail.com")
    )

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: MutableState<Boolean> get() = _isLoading

    private val _isLoggedIn: MutableState<Boolean> = mutableStateOf(false)
    val isLoggedIn: MutableState<Boolean> get() = _isLoggedIn

    private val _email: MutableState<String> = mutableStateOf("")
    val email: State<String> get() = _email

    private val _emailTouched: MutableState<Boolean> = mutableStateOf(false)
    val emailTouched: State<Boolean> get() = _emailTouched

    private val _emailError: MutableState<Boolean> = mutableStateOf(false)
    val emailError: State<Boolean> get() = _emailError

    private val _password: MutableState<String> = mutableStateOf("")
    val password: State<String> get() = _password

    private val _passwordTouched: MutableState<Boolean> = mutableStateOf(false)
    val passwordTouched: State<Boolean> get() = _passwordTouched

    private val _passwordError: MutableState<Boolean> = mutableStateOf(false)
    val passwordError: State<Boolean> get() = _passwordError

    private val _showGoogleAuth: MutableState<Boolean> = mutableStateOf(false)
    val showGoogleAuth: State<Boolean> get() = _showGoogleAuth

    private val _user: MutableState<User?> = mutableStateOf(null)
    val user: MutableState<User?> get() = _user


    fun setEmail(v: String) {
        _email.value = v
    }

    fun setPassword(v: String) {
        _password.value = v
    }

    fun setEmailError(v: Boolean) {
        _emailError.value = v
    }

    fun setPasswordError(v: Boolean) {
        _passwordError.value = v
    }

    fun setEmailTouched(v: Boolean) {
        _emailTouched.value = v
    }

    fun setPasswordTouched(v: Boolean) {
        _passwordTouched.value = v
    }

    fun setShowGoogleAuth(v: Boolean) {
        _showGoogleAuth.value = v
    }

    fun loginUser(user: User) {
        _isLoading.value = true
        _user.value = user
    }

    fun doneLoading() {
        viewModelScope.launch {
            delay(1500)
            _isLoading.value = false
            setShowGoogleAuth(false)
        }
    }

    fun doneLoggedIn() {
        _isLoggedIn.value = true
    }
}