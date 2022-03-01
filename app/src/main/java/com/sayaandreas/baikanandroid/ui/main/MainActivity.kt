package com.sayaandreas.baikanandroid.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sayaandreas.baikanandroid.ui.theme.BaikanAndroidTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            splashScreenViewProvider.iconView
                .animate()
                .setDuration(
                    2000L
                )
                .alpha(0f)
                .withEndAction {
                    splashScreenViewProvider.remove()
                }.start()
        }

        val mainViewModel: MainViewModel by viewModels()
        setContent {
            BaikanAndroidTheme {
                Baikan(mainViewModel)
            }
        }
    }
}