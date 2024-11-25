package com.saibabui.spendit.home.contactscreen.data

import com.saibabui.spendit.home.contactscreen.ContactDetails

interface ContactRepository {
    suspend fun getContactDetails() : List<ContactDetails>
}
