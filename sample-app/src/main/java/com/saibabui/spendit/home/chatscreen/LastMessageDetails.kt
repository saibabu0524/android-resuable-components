package com.saibabui.spendit.home.chatscreen

import java.sql.Timestamp

data class LastMessageDetails(
    val lastMessageSenderId : String,
    val timestamp: Timestamp,
    val message : String
)
