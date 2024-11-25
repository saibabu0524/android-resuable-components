package com.saibabui.spendit.home.chatscreen

data class ChatRoomModel(
    var userId : String,
    var users : List<String>,
    var lastMessageDetails: LastMessageDetails,
)
