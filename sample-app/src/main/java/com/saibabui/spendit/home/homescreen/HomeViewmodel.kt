package com.saibabui.spendit.home.homescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saibabui.spendit.home.homescreen.presentation.ChatRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val getChatRoomListUseCase: GetChatRoomListUseCase
) : ViewModel() {

    private val _chatRoomListState = MutableStateFlow<List<ChatRoom>>(emptyList())
    val chatRoomListState: StateFlow<List<ChatRoom>> = _chatRoomListState

    init {
//        fetchChatRooms()
    }

   fun fetchChatRooms(userId :String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val chatRooms = getChatRoomListUseCase.execute(userId)
                Log.d("HomeViewmodel",chatRooms.toString())
            } catch (e: Exception) {
                Log.d("HomeViewmodel","Error occurred")
            }
        }
    }
}