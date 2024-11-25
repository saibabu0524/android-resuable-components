package com.saibabui.spendit.home.callscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saibabui.spendit.home.homescreen.presentation.CommonHeader
import com.saibabui.spendit.R


@Composable
fun CallScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CommonHeader(
            stringResource(R.string.call), painterResource(id = R.drawable.call_user_header_icon),
            modifier = Modifier
                .width(41.dp)
                .height(41.dp)
                .background(Color(0x33FFFFFF), shape = CircleShape)
                .padding(10.dp)
        )
    }
}