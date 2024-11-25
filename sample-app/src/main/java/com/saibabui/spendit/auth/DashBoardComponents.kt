package com.saibabui.spendit.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.saibabui.spendit.auth.model.SocialMediaIcon
import com.saibabui.spendit.auth.navigation.AuthScreen
import com.saibabui.spendit.R

@Composable
fun DashBoardHeading(modifier: Modifier = Modifier , heading : String) {
    Text(
        text = heading,
        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 68.sp,
        lineHeight = 78.sp,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .then(modifier)
    )
}

@Composable
fun HeadingShortDescription(modifier: Modifier = Modifier , shortDescription: String) {
    Text(
        text = shortDescription,
        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 26.sp,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .then(modifier)
    )
}

@Composable
fun SocialMediaIconsList(imageList: List<SocialMediaIcon>) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        imageList.forEach {
            Image(
                painter = it.painter,
                contentDescription = it.contentDescription,
                Modifier
                    .background(MaterialTheme.colorScheme.onSurfaceVariant, CircleShape)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}


@Composable
fun DividerOr(divideString: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            thickness = 1.dp,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onBackground)
                .alpha(0.1f)
                .weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = divideString,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            fontWeight = FontWeight.Black,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Divider(
            thickness = 1.dp,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onBackground)
                .alpha(0.1f)
                .weight(1f)
        )
    }
}

@Composable
fun SignUpWithMailButton(navController: NavController , buttonText : String) {
    Button(
        onClick = { navController.navigate(AuthScreen.SignUp.route) },
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = buttonText,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            textAlign = TextAlign.Center,
        )
    }
}


@Composable
fun LoginLinkForExistingAccount(navController: NavController) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = stringResource(R.string.existing_account), fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 14.sp,
        )
        Text(
            text = stringResource(R.string.login), fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            modifier = Modifier.clickable {
                navController.navigate(AuthScreen.Login.route)
            }
        )
    }
}

@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

//
//@Preview(showBackground = true)
//@Composable
//private fun ComponentPrev() {
//    Column {
//        DashBoardHeading()
//        HeadingShortDescription()
//        DividerOr()
//        SignUpWithMailButton()
//        LoginLinkForExistingAccount()
//    }
//}