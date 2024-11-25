package com.saibabui.spendit.auth.singup.presentation.model

sealed class SignUpFormEvents {
    data class NameChangedEvent(val name: String) : SignUpFormEvents()
    data class EmailChangedEvent(val email: String) : SignUpFormEvents()
    data class PasswordChangedEvent(val password: String) : SignUpFormEvents()
    data class RepeatedPasswordChangedEvent(val repeatedPassword: String) : SignUpFormEvents()
    data object SignUpButtonEvent : SignUpFormEvents()
}