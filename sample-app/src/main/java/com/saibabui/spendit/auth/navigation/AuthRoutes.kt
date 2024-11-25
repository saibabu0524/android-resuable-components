package com.saibabui.spendit.auth.navigation


sealed class AuthScreen(val route: String) {
    data object OnBoardingScreen : AuthScreen("onBoardingScreen")
    data object Login : AuthScreen("login")
    data object SignUp : AuthScreen("signup")
}

sealed class Home(val route: String) {
    data object DemoHomeScreen : Home("demoHomeScreen")
    data object HomeScreen : Home("homeScreen")
    data object ContactScreen : Home("contactScreen")
    data object SettingsScreen : Home("settingScreen")
    data object CallScreen : Home("callScreen")
    data object ChatScreen : Home("chatScreen")
    data object ProfileScreen : Home("profileScreen")
}



