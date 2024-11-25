package com.saibabui.spendit.mainnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saibabui.spendit.home.navigation.Home
import com.saibabui.spendit.auth.navigation.authNavGraph
import com.saibabui.spendit.common.Graph

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.HOME) {
            Home()
        }
    }
}