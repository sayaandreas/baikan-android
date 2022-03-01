package com.sayaandreas.baikanandroid

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

enum class BaikanScreen(
    val icon: ImageVector?,
) {
    Home(
        icon = Icons.Filled.Home,
    ),
    ChooseTopic(
        icon = Icons.Filled.Email,
    ),
    Profile(
        icon = Icons.Filled.AccountCircle,
    ),
    Notifications(
        icon = Icons.Filled.Notifications,
    ),
    TopicIntroScreen(
        icon = null,
    ),
    ChooseServiceScreen(
        icon = null,
    ),
    ChoosePackageScreen(
        icon = null
    ),
    ChooseCounselorScreen(
        icon = null
    ),
    CounselingAgreementScreen(
        icon = null
    ),
    OrderDetailScreen(
        icon = null
    ),
    PaymentMethodScreen(
        icon = null
    ),
    ChooseBankScreen(
        icon = null
    ),
    PaymentDetailScreen(
        icon = null
    ),
    PaymentSuccessScreen(
        icon = null
    ),
    CallCounselorScreen(
        icon = null
    ),
    LoginScreen(
        icon = null
    ),
    RegisterScreen(
        icon = null
    ),
    OnboardingScreen(
        icon = null
    ),
    WelcomeScreen(
        icon = null
    );

    companion object {
        fun fromRoute(route: String?): BaikanScreen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                ChooseTopic.name -> ChooseTopic
                Profile.name -> Profile
                Notifications.name -> Notifications
                TopicIntroScreen.name -> TopicIntroScreen
                ChooseServiceScreen.name -> ChooseServiceScreen
                ChoosePackageScreen.name -> ChoosePackageScreen
                ChooseCounselorScreen.name -> ChooseCounselorScreen
                CounselingAgreementScreen.name -> CounselingAgreementScreen
                OrderDetailScreen.name -> OrderDetailScreen
                PaymentMethodScreen.name -> PaymentMethodScreen
                ChooseBankScreen.name -> ChooseBankScreen
                PaymentDetailScreen.name -> PaymentDetailScreen
                PaymentSuccessScreen.name -> PaymentSuccessScreen
                CallCounselorScreen.name -> CallCounselorScreen
                LoginScreen.name -> LoginScreen
                RegisterScreen.name -> RegisterScreen
                OnboardingScreen.name -> OnboardingScreen
                WelcomeScreen.name -> WelcomeScreen
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}