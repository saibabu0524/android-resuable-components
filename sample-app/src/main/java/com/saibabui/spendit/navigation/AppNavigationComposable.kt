package com.saibabui.spendit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saibabui.spendit.ui.login.mobile_number.MobileNumberScreen

@Composable
fun AppNavigationComposable(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AuthJourneyRoutes.OnBoarding.route) {
        composable(AuthJourneyRoutes.OnBoarding.route) {
        //    AuthJourneyRoutes.OnBoarding(navController = navController)
        }
        composable(AuthJourneyRoutes.MobileNumber.route) {
            MobileNumberScreen(navController)
        }
    }
}
