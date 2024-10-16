package com.saibabui.spendit.navigation

sealed class AuthJourneyRoutes(val route : String) {
    object OnBoarding : AuthJourneyRoutes("onboarding")
    object MobileNumber : AuthJourneyRoutes("mobile_number")
    object MobileNumberVerification : AuthJourneyRoutes("mobile_number_verification")
    object Login : AuthJourneyRoutes("login")
    object SignUp : AuthJourneyRoutes("signup")
    object ForgotPassword : AuthJourneyRoutes("forgot_password")
}