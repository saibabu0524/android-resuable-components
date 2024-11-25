package com.saibabui.spendit.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.saibabui.spendit.auth.navigation.Home
import com.saibabui.spendit.common.Graph
import com.saibabui.spendit.home.callscreen.CallScreen
import com.saibabui.spendit.home.chatscreen.ChatScreen
import com.saibabui.spendit.home.contactscreen.ContactScreen
import com.saibabui.spendit.home.homescreen.presentation.HomeScreen
import com.saibabui.spendit.home.profile.ProfileScreen
import com.saibabui.spendit.home.settingscreen.SettingsScreen

@Composable
fun HomeNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = Home.HomeScreen.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Home.HomeScreen.route) {
            HomeScreen(onClickChatRoom = {
                navController.navigate(Home.ChatScreen.route)
            },paddingValues)
        }
        composable(Home.ContactScreen.route) { ContactScreen(navController = navController) }
        composable(Home.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
        composable(Home.CallScreen.route) {
            CallScreen()
        }
        composable(Home.ChatScreen.route) {
            ChatScreen()
        }
        composable(
            Home.ProfileScreen.route + "/{name}/{gmail}/{userId}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
            },
                navArgument("gmail") {
                    type = NavType.StringType
                },
                navArgument("userId"){
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            ProfileScreen(navBackStackEntry = backStackEntry,navController)
        }
    }
}