package com.sayaandreas.baikanandroid.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sayaandreas.baikanandroid.ui.counseling.*
import com.sayaandreas.baikanandroid.ui.home.HomeScreen
import com.sayaandreas.baikanandroid.ui.onboarding.LoginScreen
import com.sayaandreas.baikanandroid.ui.onboarding.OnboardingScreen
import com.sayaandreas.baikanandroid.ui.onboarding.RegisterScreen
import com.sayaandreas.baikanandroid.ui.onboarding.WelcomeScreen
import com.sayaandreas.baikanandroid.ui.payment.*

@Composable
fun Baikan(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = BaikanScreen.Onboarding.route) {
        composable(BaikanScreen.Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(BaikanScreen.Login.route) {
            LoginScreen(navController, mainViewModel)
        }
        composable(BaikanScreen.Register.route) {
            RegisterScreen(navController, mainViewModel)
        }
        composable(BaikanScreen.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(BaikanScreen.Home.route) {
            HomeScreen(navController)
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
            ChooseCounselorScreen(navController)
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
            CallCounselorScreen(navController)
        }
    }
}