package com.saibabui.spendit.auth.singup.domain

data class ValidationResult(
    val isSuccessful: Boolean = false,
    val errorMessage: String = ""
)