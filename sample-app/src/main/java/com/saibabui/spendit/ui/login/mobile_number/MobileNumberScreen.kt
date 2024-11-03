package com.saibabui.spendit.ui.login.mobile_number

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.saibabui.base.TopAppBarComposable
import com.saibabui.spendit.R


@Composable
fun MobileNumberScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colorScheme.primary)
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBarComposable(arrowBackIcon = R.drawable.ic_back_arrow){
            navController.popBackStack()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MobileNumberScreenPreview() {
    val navController = NavController(LocalContext.current)
    MobileNumberScreen(navController)
}