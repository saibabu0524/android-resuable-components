package com.saibabui.spendit.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.saibabui.spendit.R
import com.saibabui.spendit.navigation.AuthJourneyRoutes

@Composable
fun OnBoardingScreen(modifier: Modifier = Modifier,navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_onboarding_background),
            contentDescription = "background image",
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_brand),
                contentDescription = stringResource(
                    id = R.string.brand_icon_cd
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = "Welcome\n to our store",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 48.sp,
                    lineHeight = 48.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )
            Text(
                text = "Ger your groceries in as fast as one hour",
                color = MaterialTheme.colorScheme.onPrimary
            )
            Button(
                onClick = {
                    navController.navigate(AuthJourneyRoutes.MobileNumber.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Get Started",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


@Preview
@Composable
private fun DashBoardScreenPreview() {
    val navController = NavController(LocalContext.current)
    OnBoardingScreen(navController = navController)
}