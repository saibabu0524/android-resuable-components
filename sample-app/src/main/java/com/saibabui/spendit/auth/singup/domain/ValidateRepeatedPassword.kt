package com.saibabui.spendit.auth.singup.domain

import com.saibabui.spendit.auth.singup.domain.ValidationResult

class ValidateRepeatedPassword {
    fun execute(password: String, repeatedPassword: String): ValidationResult {
        return if (password == repeatedPassword) {
            ValidationResult(true, "")
        } else {
            ValidationResult(
                false,
                "The Password and conform password should be same"
            )
        }
    }
}