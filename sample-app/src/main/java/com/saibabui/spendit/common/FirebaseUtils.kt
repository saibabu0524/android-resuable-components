package com.saibabui.spendit.common

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseUtils {
    fun getCurrentUserId(): String? = FirebaseAuth.getInstance().uid

    fun getCurrentUserDetails() =
        getCurrentUserId()?.let {
            FirebaseFirestore.getInstance().collection("users").document(
                it
            )
        }

    fun getChatRoomDetails(
        chatRoomName: String
    ) = FirebaseFirestore.getInstance().collection("chatrooms").document(chatRoomName)


    fun getUsersCollection() = FirebaseFirestore.getInstance().collection("users0")


    fun getChatRoomId(user1: String, user2: String): String {
        return if (user1.hashCode() > user2.hashCode()) {
            user1 + "_" + user2
        } else {
            user2 + "_" + user1
        }
    }

}