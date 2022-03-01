package com.sayaandreas.baikanandroid.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayaandreas.baikanandroid.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    private val _user: MutableState<User?> = mutableStateOf(null)
    val user: State<User?> get() = _user

    private val _selectedTopic: MutableState<String> = mutableStateOf("")
    val selectedTopic: State<String> get() = _selectedTopic

    init {
        viewModelScope.launch {
            delay(2000)
            doneLoading()
        }
    }

    private fun doneLoading() {
        _isLoading.value = false
    }

    fun selectTopic(topic: String) {
        _selectedTopic.value = topic
    }

    fun setCurrentUser(user: User) {
        _user.value = user
    }
}