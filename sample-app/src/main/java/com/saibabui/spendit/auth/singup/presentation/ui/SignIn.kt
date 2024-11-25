package com.saibabui.spendit.auth.singup.presentation.ui


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.saibabui.spendit.auth.ProgressBar
import com.saibabui.spendit.auth.singup.presentation.viewmodel.SignUpViewmodel
import com.saibabui.spendit.common.Graph
import com.saibabui.spendit.common.NetworkResult
import com.saibabui.spendit.common.UserDetails

@Composable
fun SignIn(
    viewModel: SignUpViewmodel = hiltViewModel(),
    navController: NavController,
    signUpSucceed: () -> Unit,
    showErrorMessage: (errorMessage: String?) -> Unit
) {
    when (val signInResponse = viewModel.signUpResponse.collectAsState().value) {
        is NetworkResult.Loading -> ProgressBar()
        is NetworkResult.Success -> {
            if (signInResponse.data == true) {
                LaunchedEffect(key1 = true) {
                    signUpSucceed()
                    viewModel.getCurrentUserId()?.let {
                        UserDetails(
                            viewModel.nameState.value.value,
                            it,
                            viewModel.emailState.value.value
                        )
                    }?.let {
                        viewModel.adduserDetailsToPreferences(
                            it
                        )
                    }
                }
                navController.navigate(Graph.HOME)
            }
        }

        is NetworkResult.Error -> {
            showErrorMessage(signInResponse.message)
        }
    }
}