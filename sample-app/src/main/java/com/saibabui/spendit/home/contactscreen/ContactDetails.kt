package com.saibabui.spendit.home.contactscreen


data class ContactDetails(
    var name: String = "",
    var email: String = "",
    var profileImage: String? = null,
    var userId: String?
) {
    // Add a no-argument constructor
    constructor() : this("", "", null, null)
}
