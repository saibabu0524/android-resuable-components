package com.saibabui.spendit.home.contactscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saibabui.spendit.home.contactscreen.data.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {

    private val _contacts = MutableStateFlow<List<ContactDetails>>(emptyList())
    val contacts: StateFlow<List<ContactDetails>> get() = _contacts

    private var _loading = MutableStateFlow(false)
    val loading: MutableStateFlow<Boolean> get() = _loading

    init {
        fetchContacts()
    }

    private fun fetchContacts() {
        viewModelScope.launch {
            try {
                _loading.value = true
                val fetchedContacts = contactRepository.getContactDetails()
                _loading.value = false
                _contacts.value = fetchedContacts
            } catch (e: Exception) {
                _loading.value = false
                // Handle error
            }
        }
    }
}

