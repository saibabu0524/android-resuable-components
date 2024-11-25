package com.saibabui.spendit.home.profile

import com.saibabui.spendit.home.ChatRepository
import com.saibabui.spendit.home.homescreen.presentation.ChatRoom
import javax.inject.Inject

class CreateChatRoomUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend fun execute(sender: String, receiver: String) : ChatRoom {
        return chatRepository.createChatRoom(sender, receiver)
    }
}