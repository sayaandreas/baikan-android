package com.sayaandreas.baikanandroid.ui.main

import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayaandreas.baikanandroid.model.Counselor
import com.sayaandreas.baikanandroid.model.Topic
import com.sayaandreas.baikanandroid.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(sharedPref: SharedPreferences?) : ViewModel() {
    val counselorList = Counselor.getAll()
    val topicList = Topic.getAll()
    val userList = User.getAll()
    val sp = sharedPref

    private val _quick: MutableState<Boolean> = mutableStateOf(false)
    val quick: State<Boolean> get() = _quick

    private val _currentUser: MutableState<User?> = mutableStateOf(null)
    val currentUser: State<User?> get() = _currentUser

    private val _selectedTopic: MutableState<Topic?> = mutableStateOf(null)
    val selectedTopic: State<Topic?> get() = _selectedTopic

    private val _selectedCounselor: MutableState<Counselor?> = mutableStateOf(null)
    val selectedCounselor: State<Counselor?> get() = _selectedCounselor

    private val _selectedPackage: MutableState<Package?> = mutableStateOf(null)
    val selectedPackage: State<Package?> get() = _selectedPackage

    init {
        getSavedUser()
    }

    fun setQuick(b: Boolean) {
        _quick.value = b
    }

    fun selectTopic(t: Topic) {
        _selectedTopic.value = t
    }

    fun selectCounselor(c: Counselor) {
        _selectedCounselor.value = c
    }

    fun selectPackage(p: Package) {
        _selectedPackage.value = p
    }

    fun setCurrentUser(user: User) {
        _currentUser.value = user
        if (sp != null) {
            with(sp.edit()) {
                putString("savedUser", user.email)
                apply()
            }
        }
    }

    fun logoutUser() {
        _currentUser.value = null
        if (sp != null) {
            with(sp.edit()) {
                putString("savedUser", null)
                apply()
            }
        }
    }

    private fun getSavedUser() {
        if (sp != null) {
            val savedUser = sp.getString("savedUser", null)
            if (savedUser != null) {
                val user = userList.find { it.email == savedUser }
                if (user != null) setCurrentUser(user)
            }
        }
    }
}