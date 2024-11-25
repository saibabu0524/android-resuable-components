package com.saibabui.spendit.auth.singup.domain

import android.util.Patterns

class ValidateEmail {
    fun execute(email: String): ValidationResult {
        return if (email.isBlank()) {
            ValidationResult(isSuccessful = false, errorMessage = "Email could not be black.")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ValidationResult(isSuccessful = false, errorMessage = "Entered email is invalid.")
        } else {
            ValidationResult(isSuccessful = true, errorMessage = "")
        }
    }
}