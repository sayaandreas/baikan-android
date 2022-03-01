package com.sayaandreas.baikanandroid.ui.main

sealed class BaikanScreen(val route: String) {
    object Onboarding : BaikanScreen("Onboarding")
    object Login : BaikanScreen("Login")
    object Register : BaikanScreen("Register")
    object RegisterGender : BaikanScreen("RegisterGender")
    object RegisterPhone : BaikanScreen("RegisterPhone")
    object RegisterBirthDay : BaikanScreen("RegisterBirthDay")
    object RegisterAddress : BaikanScreen("RegisterAddress")
    object Welcome : BaikanScreen("Welcome")
    object Home : BaikanScreen("Home")
    object TopicIntro : BaikanScreen("TopicIntro")
    object ChooseService : BaikanScreen("ChooseService")
    object ChoosePackage : BaikanScreen("ChoosePackage")
    object ChooseCounselor : BaikanScreen("ChooseCounselor")
    object CounselingAgreement : BaikanScreen("CounselingAgreement")
    object OrderDetail : BaikanScreen("OrderDetail")
    object PaymentMethod : BaikanScreen("PaymentMethod")
    object ChooseBank : BaikanScreen("ChooseBank")
    object PaymentDetail : BaikanScreen("PaymentDetail")
    object PaymentSuccess : BaikanScreen("PaymentSuccess")
    object CallCounselor : BaikanScreen("CallCounselor")
}