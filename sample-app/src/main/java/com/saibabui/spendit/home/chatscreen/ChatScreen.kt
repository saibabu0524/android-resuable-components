package com.saibabui.spendit.home.chatscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saibabui.spendit.R


@Composable
fun ChatScreen() {
    Column {
        Scaffold(
            topBar = { ChatScreenHeader() }, bottomBar = { ChatScreenBottomBar() }
        ) {
            Box(
                modifier = Modifier
                    .padding(paddingValues = it)
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    ChatMessageDate(stringResource(R.string.today))
                    ChatMessage()
                }
            }
        }
    }
}


@Composable
fun ChatScreenHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.back_icon), contentDescription = stringResource(
                R.string.back
            )
            )
            Image(
                painter = painterResource(id = R.drawable.sample_profile_image),
                contentDescription = stringResource(R.string.user_profile_image),
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )
            Column {
                Text(
                    text = "Jhon Abraham",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium, lineHeight = 16.sp,
                    color = Color(0xFF000E08),
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = stringResource(R.string.active_now),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal, lineHeight = 14.sp,
                    color = Color(0x80797C7B)
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(18.dp)) {
            Image(
                painter = painterResource(id = R.drawable.audio_call_icon),
                contentDescription = ""
            )
            Image(
                painter = painterResource(id = R.drawable.video_call_icon),
                contentDescription = ""
            )
        }
    }
}


@Composable
fun ChatMessage() {
    Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFF3D4A7A),
                    shape = RoundedCornerShape(10.dp, 0.dp, 10.dp, 10.dp)
                )
                .padding(vertical = 5.dp, horizontal = 5.dp)
        ) {
            Text(
                text = "",
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 12.sp,
                color = Color.White
            )
        }
        Text(
            text = "", fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 10.sp,
            color = Color(0x80797C7B)
        )
    }
}

@Composable
fun ChatScreenBottomBar() {
    Card(elevation = 10.dp) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    16
                        .dp
                )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.attach_files_icon),
                contentDescription = "add file",
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFF3F6F6),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .weight(6f)
                    .padding(5.dp), contentAlignment = Alignment.CenterEnd
            ) {
//                var message by remember {
//                    mutableStateOf("")
//                }
//                TextField(value = message, onValueChange = {
//                    message = it
//                })
                Icon(
                    painter = painterResource(id = R.drawable.send_files_icon),
                    contentDescription = ""
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.camera_icon),
                contentDescription = "",
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(id = R.drawable.microphone_icon),
                contentDescription = "",
                modifier = Modifier.weight(1f)
            )
        }
    }

}


@Composable
fun ChatMessageDate(date: String) {
    Text(
        text = date,
        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 12.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Preview(showSystemUi = true)
@Composable
private fun Sample() {
    ChatScreen()
}