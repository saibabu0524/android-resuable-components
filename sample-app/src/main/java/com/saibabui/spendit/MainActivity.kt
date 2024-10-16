package com.saibabui.spendit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.saibabui.spendit.navigation.AppNavigationComposable
import com.saibabui.spendit.ui.theme.SpendItTheme
import com.saibabui.spendit.ui.onboarding.OnBoardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()
        setContent {
            SpendItTheme {
                Surface {
                    val navController = rememberNavController()
                    AppNavigationComposable(navController)
                }
            }
        }
    }
}