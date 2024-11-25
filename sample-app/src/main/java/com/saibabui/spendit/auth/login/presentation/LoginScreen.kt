package com.saibabui.spendit.auth.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.saibabui.base.TopAppBarComposable
import com.saibabui.spendit.auth.CustomButton
import com.saibabui.spendit.auth.DividerOr
import com.saibabui.spendit.auth.EmailTextFieldWithLabel
import com.saibabui.spendit.auth.LoginDescription
import com.saibabui.spendit.auth.LoginHeadingText
import com.saibabui.spendit.auth.NavigationText
import com.saibabui.spendit.auth.SocialMediaIconsList
import com.saibabui.spendit.auth.TopBar
import com.saibabui.spendit.auth.model.SocialMediaIcon
import com.saibabui.spendit.auth.singup.presentation.model.SignUpFormEvents
import com.saibabui.spendit.R

@Composable
fun LoginScreenBody(
    paddingValues: PaddingValues = PaddingValues(20.dp),
    navController: NavController,
    viewmodel: LoginViewmodel
) {
    val socialMediaIcons = listOf(
        SocialMediaIcon(
            painter = painterResource(
                id = R.drawable.facebook,
            ),
            stringResource(id = R.string.facebook_icon)
        ),
        SocialMediaIcon(
            painter = painterResource(
                id = R.drawable.google,
            ),
            stringResource(id = R.string.google_icon)
        ),
        SocialMediaIcon(
            painter = painterResource(
                id = R.drawable.apple,
            ),
            stringResource(id = R.string.apple_icon)
        )
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.surface)
            .imePadding()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 40.dp)
        ) {
            LoginHeadingText(stringResource(R.string.log_in_to_chatbox))
            LoginDescription(stringResource(R.string.welcome_back_sign_in_using_email_to_continue_us))
            SocialMediaIconsList(imageList = socialMediaIcons)
            DividerOr(stringResource(R.string.or))
            EmailTextFieldWithLabel(
                supportingText = stringResource(R.string.your_email), onValueChange = {
                    viewmodel.validate(
                        SignUpFormEvents.EmailChangedEvent(it)
                    )
                }, internalState = viewmodel.emailState
            )
            EmailTextFieldWithLabel(
                stringResource(R.string.password),
                internalState = viewmodel.passwordState
            ) {
                viewmodel.validate(
                    SignUpFormEvents.PasswordChangedEvent(it)
                )

            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(start = 20.dp, top = 40.dp, end = 20.dp, bottom = 20.dp)
        ) {
            CustomButton(buttonText = stringResource(id = R.string.login)) {
                viewmodel.validate(SignUpFormEvents.SignUpButtonEvent)
            }
            NavigationText()
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    val viewmodel: LoginViewmodel = hiltViewModel()
    Scaffold(
        topBar = {
            TopAppBarComposable(arrowBackIcon = R.drawable.back_icon) {
                navController.popBackStack()
            }
        }
    ) { paddingValues ->
//        LogIn(navController = navController) {
//            Log.d("Firebase", "Failed")
//        }
        LoginScreenBody(paddingValues, navController, viewmodel = viewmodel)
    }
}