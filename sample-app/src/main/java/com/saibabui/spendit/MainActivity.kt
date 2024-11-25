package com.saibabui.spendit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.saibabui.spendit.home.navigation.Home
import com.saibabui.spendit.mainnavigation.RootNavigationGraph
import com.saibabui.spendit.ui.theme.SpendItTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var currentUser: FirebaseUser? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        currentUser = auth.currentUser
        actionBar?.hide()
        setContent {
            SpendItTheme {
                Surface {
                    App(currentUser)
                }
            }
        }
    }
}



@Composable
fun App(currentUser: FirebaseUser?) {
    val navController = rememberNavController()

    if (currentUser == null) {
        RootNavigationGraph(navController)
    } else {
        Home()
    }
}
