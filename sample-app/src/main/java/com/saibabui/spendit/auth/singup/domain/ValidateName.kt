package com.saibabui.spendit.auth.singup.domain

class ValidateName {
    fun execute(name: String): ValidationResult {
        return if (name.isBlank()) {
            ValidationResult(isSuccessful = false, errorMessage = "Please enter name")
        } else if (name.length < 2) {
            ValidationResult(
                isSuccessful = false,
                errorMessage = "Name should contain more than 2 characters"
            )
        } else {
            ValidationResult(isSuccessful = true, errorMessage = "")
        }
    }
}