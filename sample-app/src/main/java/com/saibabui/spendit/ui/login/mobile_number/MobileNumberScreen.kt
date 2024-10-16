package com.saibabui.spendit.ui.login.mobile_number

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.saibabui.base.TopAppBarComposable
import com.saibabui.spendit.R


@Composable
fun MobileNumberScreen(navController: NavController) {
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