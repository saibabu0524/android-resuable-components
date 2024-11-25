package com.saibabui.spendit.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saibabui.spendit.home.homescreen.presentation.ChatRoom
import com.saibabui.spendit.home.profile.CreateChatRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val createChatRoomUseCase: CreateChatRoomUseCase
) : ViewModel() {
    private val _selectedChatRoom = MutableStateFlow<ChatRoom?>(null)
    val selectedChatRoom: StateFlow<ChatRoom?> = _selectedChatRoom.asStateFlow()

    fun createChatRoom(sender: String, receiver: String) {
        viewModelScope.launch {
            val chatRoom = createChatRoomUseCase.execute(sender, receiver)
            _selectedChatRoom.value = chatRoom
        }
    }
}