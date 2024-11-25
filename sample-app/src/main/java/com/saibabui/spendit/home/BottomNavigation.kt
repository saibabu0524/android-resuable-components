package com.saibabui.spendit.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.saibabui.spendit.auth.navigation.Home
import com.saibabui.spendit.R

//
//@Composable
//fun HomeScreenWithBottomNavigation(navController: NavHostController) {
//    val items = listOf(
//        BottomNavigationModel(
//            Home.HomeScreen.route,
//            "Message",
//            painterResource(id = R.drawable.message_nav_icon)
//        ),
//        BottomNavigationModel(
//            Home.CallScreen.route,
//            "Calls",
//            painterResource(id = R.drawable.call_bottom_nav_icon)
//        ),
//        BottomNavigationModel(
//            Home.ContactScreen.route,
//            "Contacts",
//            painterResource(id = R.drawable.profile_nav_icon)
//        ),
//        BottomNavigationModel(
//            Home.SettingsScreen.route,
//            "Settings",
//            painterResource(id = R.drawable.settings_nav_icon)
//        )
//    )
//
//    Scaffold(
//        bottomBar = {
//            BottomNavigation(backgroundColor = Color.White) {
//                val navBackStackEntry by navController.currentBackStackEntryAsState()
//                val currentDestination = navBackStackEntry?.destination
//                items.forEach { screen ->
//                    BottomNavigationItem(
//                        icon = { Icon(screen.icon, contentDescription = null) },
//                        label = { Text(screen.label) },
//                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
//                        onClick = {
//                            navController.navigate(screen.route) {
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    ) { innerPadding ->
//        NavHost(
//            navController,
//            startDestination = Home.HomeScreen.route,
//            Modifier.padding(innerPadding)
//        ) {
//            composable(Home.ContactScreen.route) { ContactScreen() }
//            composable(Home.SettingsScreen.route) {
//                SettingsScreen()
//            }
//            composable(Home.CallScreen.route) {
//                CallScreen()
//            }
//            composable(Home.ChatScreen.route) {
//               ChatScreen()
//            }
//        }
//    }
//}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavigationModel(
            Home.HomeScreen.route,
            "Message",
            painterResource(id = R.drawable.message_nav_icon)
        ),
        BottomNavigationModel(
            Home.CallScreen.route,
            "Calls",
            painterResource(id = R.drawable.call_bottom_nav_icon)
        ),
        BottomNavigationModel(
            Home.ContactScreen.route,
            "Contacts",
            painterResource(id = R.drawable.profile_nav_icon)
        ),
        BottomNavigationModel(
            Home.SettingsScreen.route,
            "Settings",
            painterResource(id = R.drawable.settings_nav_icon)
        )
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    val bottomBarDestination = items.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)
        ) {
            items.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomNavigationModel,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(
                text = screen.label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
        },
        icon = {
            androidx.compose.material.Icon(
                painter = screen.icon,
                contentDescription = "Navigation Icon",
                tint = if (currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true) {
                    MaterialTheme.colorScheme.primary
                } else {
                    LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
                }
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
    )
}