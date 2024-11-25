package com.saibabui.spendit.auth.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.saibabui.spendit.auth.DashBoardHeading
import com.saibabui.spendit.auth.DividerOr
import com.saibabui.spendit.auth.HeadingShortDescription
import com.saibabui.spendit.auth.LoginLinkForExistingAccount
import com.saibabui.spendit.auth.SignUpWithMailButton
import com.saibabui.spendit.auth.SocialMediaIconsList
import com.saibabui.spendit.auth.model.SocialMediaIcon
import com.saibabui.spendit.R
import com.saibabui.spendit.ui.theme.SpendItTheme


@Composable
fun DashBoardScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val socialMediaIcons = listOf(
        SocialMediaIcon(
            painter = painterResource(
                id = R.drawable.facebook_social_icon,
            ),
            "Facebook icon"
        ),
        SocialMediaIcon(
            painter = painterResource(
                id = R.drawable.google_social_icon,
            ),
            "Google icon"
        ),
        SocialMediaIcon(
            painter = painterResource(
                id = R.drawable.apple_social_icon,
            ),
            "Apple icon"
        )
    )
    Column(
        modifier = Modifier
            .then(modifier)
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DashBoardHeading(heading = stringResource(R.string.dashboard_message))
        HeadingShortDescription(shortDescription = stringResource(R.string.dashboard_short_description))
        SocialMediaIconsList(socialMediaIcons)
        DividerOr(stringResource(R.string.or))
        SignUpWithMailButton(navController, stringResource(R.string.sign_up_with_mail))
        LoginLinkForExistingAccount(navController)
    }
}


@Preview
@Composable
private fun DashBoardPreview() {
    SpendItTheme(darkTheme = true) {
        Surface {
            DashBoardScreen(navController = NavController(LocalContext.current))
        }
    }
}


