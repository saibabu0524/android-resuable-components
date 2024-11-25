package com.saibabui.spendit.home.homescreen.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saibabui.spendit.common.FirebaseUtils
import com.saibabui.spendit.home.homescreen.HomeViewmodel
import com.saibabui.spendit.R
import com.saibabui.spendit.ui.theme.SpendItTheme


@Composable
fun HomeScreen(
    onClickChatRoom: () -> Unit,paddingValues: PaddingValues
) {

    val homeViewmodel: HomeViewmodel = hiltViewModel()
    LaunchedEffect(true) {
        FirebaseUtils.getCurrentUserId()?.let { homeViewmodel.fetchChatRooms(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CommonHeader(
            screenName = stringResource(R.string.home),
            icon = painterResource(id = R.drawable.sample_profile_image),
            modifier = Modifier
                .width(44.dp)
                .height(44.dp)
        )
        HomeStatusBar()
        ChatListComponent(onClickChatRoom)
    }
}


@Composable
fun CommonHeader(
    screenName: String,
    icon: Painter?,
    modifier: Modifier,
    leadingIcon: Painter? = null
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = if (leadingIcon == null) painterResource(id = R.drawable.search_icon) else leadingIcon,
                contentDescription = "chat search icon",
            )
        }
        Text(
            text = screenName,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        if (icon != null) {
            Icon(
                painter = icon,
                contentDescription = "Profile photo",
                modifier = Modifier.then(modifier),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        } else {
            Box(
                modifier = Modifier
                    .width(41.dp)
                    .height(41.dp)
                    .background(Color.Transparent)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CommonHeaderPrev() {
    SpendItTheme {
        CommonHeader(screenName = "Settings", icon = null, modifier = Modifier)
    }
}


@Composable
fun HomeStatusBar() {
    Row {
        LazyRow() {
            items(10) {
                StatusPreviewImage()
            }
        }
    }
}


@Composable
fun StatusPreviewImage() {
    Column(horizontalAlignment = Alignment.CenterHorizontally , modifier = Modifier.padding(top = 20.dp)) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(10.dp)
                .border(BorderStroke(2.dp, Color.Gray), shape = CircleShape)

        ) {
            Image(
                painter = painterResource(id = R.drawable.sample_profile_image),
                contentDescription = "Profile photo",
                modifier = Modifier
                    .padding(5.dp)
                    .width(58.dp)
                    .height(58.dp)
            )
        }

        Text(
            text = stringResource(id = R.string.name),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
    }
}


@Composable
fun ChatListComponent(onClickChatRoom: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFE6E6E6), shape = RoundedCornerShape(10.dp))
                    .width(40.dp)
                    .height(4.dp)
            )
            ChatList(onClickChatRoom)
        }
    }
}


@Composable
fun ChatList(onClickChatRoom: () -> Unit) {
    LazyColumn {
        items(10) {
            ChatListItem(onClickChatRoom)
        }
    }
}


@Composable
fun ChatListItem(onClickChatRoom: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable {
                onClickChatRoom()
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.sample_profile_image),
                contentDescription = "Profile photo",
                modifier = Modifier
                    .width(52.dp)
                    .height(52.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Text(
                    text = "Alex Linderson",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = Color(0xFF000E08)
                )
                Text(
                    text = "How are you today?",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color(0x797C7B80)
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "2 min ago",
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                color = Color(0x797C7B80)
            )
            Box(
                modifier = Modifier.background(color = Color.Red, shape = CircleShape),
            ) {
                Text(
                    text = "1",
                    modifier = Modifier
                        .background(color = Color.Transparent)
                        .width(20.dp)
                        .height(20.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    color = Color(0x797C7B80),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
private fun HomeHeaderPrev() {
//    ChatListItem()
}