package com.saibabui.spendit.home.homescreen

import com.google.firebase.firestore.FirebaseFirestore
import com.saibabui.spendit.home.ChatRepository
import com.saibabui.spendit.home.homescreen.presentation.ChatRoom
import javax.inject.Inject


class GetChatRoomListUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val firebaseFireStore: FirebaseFirestore
) {
    suspend fun execute(userId: String): List<ChatRoom> {
        return chatRepository.getAllChatRoomDetails(userId, firebaseFireStore)
    }
}
