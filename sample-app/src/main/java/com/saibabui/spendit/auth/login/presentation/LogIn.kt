package com.saibabui.spendit.auth.login.presentation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.saibabui.spendit.auth.ProgressBar
import com.saibabui.spendit.common.Graph
import com.saibabui.spendit.common.NetworkResult

@Composable
fun LogIn(
    viewModel: LoginViewmodel = hiltViewModel(),
    navController: NavController,
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when (val loginResponse = viewModel.loginResponse.collectAsState().value) {
        is NetworkResult.Loading -> ProgressBar()
        is NetworkResult.Success -> {
            if (loginResponse.data == true) {
                LaunchedEffect(key1 = true) {

                }
                navController.navigate(Graph.HOME)
            }
        }

        is NetworkResult.Error -> {
            showErrorMessage(loginResponse.message)
        }
    }
}