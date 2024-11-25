package com.saibabui.spendit.home

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.saibabui.spendit.common.FirebaseUtils
import com.saibabui.spendit.home.homescreen.presentation.ChatRoom
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ChatRepositoryImpl(override val firestore: FirebaseFirestore) : ChatRepository {
    private val TAG = "CHAR REPOSITORY"
    override suspend fun createChatRoom(user1: String, user2: String): ChatRoom {
        return suspendCoroutine { continuation ->
            val chatRoomId = FirebaseUtils.getChatRoomId(user1, user2)
            val chatRoomData = hashMapOf(
                "user1" to user1,
                "user2" to user2,
                "chatRoomId" to chatRoomId
            )

            val chatRoomDocument = firestore.collection("chatrooms").document(chatRoomId)

            chatRoomDocument.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        handleExistingChatRoom(document, chatRoomId, user1, user2, continuation)
                    } else {
                        createNewChatRoom(
                            chatRoomData,
                            chatRoomDocument,
                            chatRoomId,
                            user1,
                            user2,
                            continuation
                        )
                    }
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error checking chat room existence", e)
                    continuation.resumeWithException(e)
                }
        }
    }

    private fun handleExistingChatRoom(
        document: DocumentSnapshot,
        chatRoomId: String,
        user1: String,
        user2: String,
        continuation: Continuation<ChatRoom>
    ) {
        Log.d(TAG, "Chat room exists")
        Log.d(TAG, document.data?.toString() ?: "")
        val chatRoom = document.toObject(ChatRoom::class.java) ?: ChatRoom(user1, user2, chatRoomId)
        Log.d(TAG, chatRoom.toString())
        continuation.resume(chatRoom)
    }

    private fun createNewChatRoom(
        chatRoomData: HashMap<String, String>,
        chatRoomDocument: DocumentReference,
        chatRoomId: String,
        user1: String,
        user2: String,
        continuation: Continuation<ChatRoom>
    ) {
        chatRoomDocument.set(chatRoomData)
            .addOnSuccessListener {
                Log.d(TAG, "Chat room created: $chatRoomId")
                val newChatRoom = ChatRoom(user1, user2, chatRoomId)
                addChatRoomToUser(user1, chatRoomData)
                addChatRoomToUser(user2, chatRoomData)
                continuation.resume(newChatRoom)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error creating chat room", e)
                continuation.resumeWithException(e)
            }
    }

    private fun addChatRoomToUser(userId: String, chatRoomData: HashMap<String, String>) {
        firestore.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (!document.exists()) {
                    Log.d("FIREBASE", "User document does not exist")
                } else {
                    firestore.collection("users").document(userId).collection("chatRoomList")
                        .add(chatRoomData)
                        .addOnSuccessListener {
                            Log.d("FIREBASE", "Chat room added to user details")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding chat room to user details", e)
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error checking user document existence", e)
            }
    }


    override suspend fun getAllChatRoomDetails(
        userId: String,
        firestore: FirebaseFirestore
    ): List<ChatRoom> = suspendCoroutine { continuation ->
        val chatRoomList = mutableListOf<ChatRoom>()
        firestore.collection("users").document(userId).collection("chatRoomList").get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Log.d("FIREBASE", "No chat rooms found for this user")
                } else {
                    for (document in documents) {
                        val chatRoomData = document.data
                        val chatRoom = document.toObject(ChatRoom::class.java)
                        chatRoomList.add(chatRoom)
                        Log.d("FIREBASE", "Chat room: $chatRoomData")
                        // Process each chat room data as needed
                    }
                }
                continuation.resume(chatRoomList) // Resume coroutine with chatRoomList
            }
            .addOnFailureListener { e ->
                Log.w("FIREBASE", "Error fetching chat rooms", e)
                continuation.resumeWithException(e) // Resume coroutine with exception
            }
    }

//    override suspend fun getAllChatRoomDetails(): List<ChatRoom> {
//        return try {
//            val querySnapshot = firestore.collection("chatrooms").get().await()
//            val chatRooms = mutableListOf<ChatRoom>()
//
//            for (document in querySnapshot.documents) {
//                val sender = document.getString("user1") ?: ""
//                val receiver = document.getString("user1") ?: ""
//                val chatRoomName = document.getString("chatRoomId") ?: ""
//
//                val chatRoom = ChatRoom(sender, receiver, chatRoomName)
//                chatRooms.add(chatRoom)
//            }
//
//            chatRooms.toList()
//        } catch (e: Exception) {
//            Log.w("FIREBASE FIRESTORE", "Error getting chat rooms", e)
//            emptyList() // Return empty list in case of error
//        }
//    }

    override suspend fun getChatMessages() {
        TODO("Not yet implemented")
    }
}