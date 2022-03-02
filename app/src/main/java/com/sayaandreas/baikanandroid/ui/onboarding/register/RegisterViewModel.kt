package com.sayaandreas.baikanandroid.ui.onboarding.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayaandreas.baikanandroid.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    val users = listOf(
        User("Johny Pramono", "johnyisworking@gmail.com"),
        User("Johny Pramono", "johnypersonal@gmail.com")
    )

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: MutableState<Boolean> get() = _isLoading

    private val _isRegistered: MutableState<Boolean> = mutableStateOf(false)
    val isRegistered: MutableState<Boolean> get() = _isRegistered

    private val _name: MutableState<String> = mutableStateOf("")
    val name: State<String> get() = _name

    private val _nameTouched: MutableState<Boolean> = mutableStateOf(false)
    val nameTouched: State<Boolean> get() = _nameTouched

    private val _nameError: MutableState<Boolean> = mutableStateOf(false)
    val nameError: State<Boolean> get() = _nameError

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

    fun setName(v: String) {
        _name.value = v
    }

    fun setEmail(v: String) {
        _email.value = v
    }

    fun setPassword(v: String) {
        _password.value = v
    }

    fun setNameError(v: Boolean) {
        _nameError.value = v
    }

    fun setEmailError(v: Boolean) {
        _emailError.value = v
    }

    fun setPasswordError(v: Boolean) {
        _passwordError.value = v
    }

    fun setNameTouched(v: Boolean) {
        _nameTouched.value = v
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

    fun registerUser(user: User) {
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

    fun doneRegister() {
        _isRegistered.value = true
    }
}