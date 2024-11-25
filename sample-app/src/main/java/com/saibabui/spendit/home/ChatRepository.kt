package com.saibabui.spendit.home

import com.google.firebase.firestore.FirebaseFirestore
import com.saibabui.spendit.home.homescreen.presentation.ChatRoom

interface ChatRepository {
    val firestore: FirebaseFirestore

    suspend fun createChatRoom(user1: String, user2: String): ChatRoom

    suspend fun getAllChatRoomDetails(userId: String, firestore: FirebaseFirestore): List<ChatRoom>

    suspend fun getChatMessages()
}