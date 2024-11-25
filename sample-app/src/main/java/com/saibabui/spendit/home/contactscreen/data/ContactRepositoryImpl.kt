package com.saibabui.spendit.home.contactscreen.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.saibabui.spendit.home.contactscreen.ContactDetails
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ContactRepository {

    private val TAG = "ContactRepository"

    override suspend fun getContactDetails(): List<ContactDetails> {
        val docRef = firestore.collection("users")
        val contactDetailsList: MutableList<ContactDetails> = mutableListOf()

        try {
            val documents = docRef.get().await()
            for (document in documents) {
                val contact = document.toObject(ContactDetails::class.java)
                contactDetailsList.add(contact)
            }
        } catch (e: Exception) {
            Log.d(TAG, "get failed with ", e)
        }

        return contactDetailsList
    }
}

