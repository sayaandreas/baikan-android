package com.sayaandreas.baikanandroid.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.ui.counseling.*
import com.sayaandreas.baikanandroid.ui.home.HomeScreen
import com.sayaandreas.baikanandroid.ui.onboarding.login.LoginScreen
import com.sayaandreas.baikanandroid.ui.onboarding.OnboardingScreen
import com.sayaandreas.baikanandroid.ui.onboarding.register.RegisterScreen
import com.sayaandreas.baikanandroid.ui.onboarding.WelcomeScreen
import com.sayaandreas.baikanandroid.ui.onboarding.register.RegisterAddressScreen
import com.sayaandreas.baikanandroid.ui.onboarding.register.RegisterBirthDayScreen
import com.sayaandreas.baikanandroid.ui.onboarding.register.RegisterGenderScreen
import com.sayaandreas.baikanandroid.ui.onboarding.register.RegisterPhoneScreen
import com.sayaandreas.baikanandroid.ui.payment.*

@Composable
fun Baikan(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val startDest =
        if (mainViewModel.currentUser.value != null) BaikanScreen.Home.route else BaikanScreen.Onboarding.route

    NavHost(navController = navController, startDestination = startDest) {
        composable(BaikanScreen.Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(BaikanScreen.Login.route) {
            LoginScreen(navController, mainViewModel)
        }
        composable(BaikanScreen.Register.route) {
            RegisterScreen(navController, mainViewModel)
        }
        composable(BaikanScreen.RegisterGender.route) {
            RegisterGenderScreen(navController)
        }
        composable(BaikanScreen.RegisterPhone.route) {
            RegisterPhoneScreen(navController)
        }
        composable(BaikanScreen.RegisterBirthDay.route) {
            RegisterBirthDayScreen(navController)
        }
        composable(BaikanScreen.RegisterAddress.route) {
            RegisterAddressScreen(navController)
        }
        composable(BaikanScreen.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(BaikanScreen.Home.route) {
            HomeScreen(navController, mainViewModel)
        }
        composable(BaikanScreen.TopicIntro.route) {
            TopicIntroScreen(navController)
        }
        composable(BaikanScreen.ChooseService.route) {
            ChooseServiceScreen(navController)
        }
        composable(BaikanScreen.ChoosePackage.route) {
            ChoosePackageScreen(navController)
        }
        composable(BaikanScreen.ChooseCounselor.route) {
            ChooseCounselorScreen(navController, mainViewModel)
        }
        composable(BaikanScreen.CounselingAgreement.route) {
            CounselingAgreementScreen(navController)
        }
        composable(BaikanScreen.OrderDetail.route) {
            OrderDetailScreen(navController)
        }
        composable(BaikanScreen.PaymentMethod.route) {
            PaymentMethodScreen(navController)
        }
        composable(BaikanScreen.ChooseBank.route) {
            ChooseBankScreen(navController)
        }
        composable(BaikanScreen.PaymentDetail.route) {
            PaymentDetailScreen(navController)
        }
        composable(BaikanScreen.PaymentSuccess.route) {
            PaymentSuccessScreen(navController)
        }
        composable(BaikanScreen.CallCounselor.route) {
            CallCounselorScreen(navController, mainViewModel)
        }
        composable(BaikanScreen.CounselorDetail.route) {
            CounselorDetailScreen(navController, mainViewModel)
        }
    }
}