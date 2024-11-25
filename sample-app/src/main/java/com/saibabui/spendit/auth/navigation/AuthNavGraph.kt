package com.saibabui.spendit.auth.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.saibabui.spendit.auth.dashboard.DashBoardScreen
import com.saibabui.spendit.auth.login.presentation.LoginScreen
import com.saibabui.spendit.auth.singup.presentation.ui.SignUpScreen
import com.saibabui.spendit.common.Graph
import com.saibabui.spendit.home.DemoHomeScreen


fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    val modifier = Modifier
        .fillMaxSize()
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.OnBoardingScreen.route
    ) {
        composable(AuthScreen.OnBoardingScreen.route) {
            DashBoardScreen(navController = navController, modifier = modifier)
        }
        composable(AuthScreen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(AuthScreen.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(Home.DemoHomeScreen.route) {
            DemoHomeScreen(navController = navController)
        }
    }
}