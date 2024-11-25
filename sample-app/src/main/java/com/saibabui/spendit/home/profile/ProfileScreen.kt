package com.saibabui.spendit.home.profile

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.saibabui.spendit.auth.navigation.Home
import com.saibabui.spendit.R


@Composable
fun ProfileHeader(navBackStackEntry: NavBackStackEntry, navController: NavController) {
    val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onBackground
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Image(
                    painter = painterResource(id = R.drawable.sample_profile_image),
                    contentDescription = "profile image",
                    modifier = Modifier
                        .width(82.dp)
                        .height(82.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = navBackStackEntry.arguments?.getString("name") ?: "Name",
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text =navBackStackEntry.arguments?.getString("gmail") ?: "email",
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(33.dp),
        ) {
            val viewModel: ProfileViewModel = hiltViewModel()
            Icon(
                painter = painterResource(id = R.drawable.profile_message),
                contentDescription = "Chat icon",
                modifier = Modifier
                    .clickable {
                        navBackStackEntry.arguments
                            ?.getString("userId")
                            ?.let {
                                if (currentUserUid != null) {
//                                createChatRoom(currentUserUid, it, navController)
                                    viewModel.createChatRoom(currentUserUid, it)
                                }
                            }
                    }
            )
            Icon(
                painter = painterResource(id = R.drawable.profile_video_icon),
                contentDescription = "video call icon",
            )
            Icon(
                painter = painterResource(id = R.drawable.profile_call_icon),
                contentDescription = "audio icon",
            )
            Icon(
                painter = painterResource(id = R.drawable.more_icon),
                contentDescription = "more icon",
            )
        }
    }
}

@Composable
fun UserProfileDetails(navBackStackEntry: NavBackStackEntry) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFE6E6E6), shape = RoundedCornerShape(10.dp))
                    .width(40.dp)
                    .height(4.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxSize()
            ) {
                navBackStackEntry.arguments?.getString("name")
                    ?.let { ProfileDetails(title = "Display Name", details = it) }
                navBackStackEntry.arguments?.getString("gmail")
                    ?.let { ProfileDetails(title = "Email address", details = it) }
                ProfileDetails(title = "Address", details = "33 street west subidbazar,sylhet")
                ProfileDetails(title = "Phone number", details = "(320) 555-0104")
                ProfileMediaShared()
            }
        }
    }
}


@Composable
fun ProfileDetails(title: String, details: String) {
    Column {
        Text(
            text = title,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 14.sp
        )
        Text(
            text = details,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 18.sp,
            modifier = Modifier.offset(5.dp)
        )
    }
}


@Composable
fun ProfileScreen(navBackStackEntry: NavBackStackEntry, navController: NavController) {
        Column(Modifier.background(MaterialTheme.colorScheme.background)){
            ProfileHeader(navBackStackEntry, navController)
            UserProfileDetails(navBackStackEntry)
    }
}

@Composable
fun ProfileMediaShared() {
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Media Shared",
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )
            Text(
                text = "See all",
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )
        }
        Row(modifier = Modifier.height(100.dp)) {

        }
    }
}


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 + size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 1000
            )
        ),
        label = ""
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB6B3B3), Color(0xFF757575), Color(0xFFB6B3B3)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}


val navigateToChatRoom: (navController: NavController) -> Unit = { navController ->
    navController.navigate(Home.ChatScreen.route)
}


@Preview
@Composable
private fun ProfilePrv() {
//    ProfileScreen()
}