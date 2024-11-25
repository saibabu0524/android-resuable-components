package com.saibabui.spendit.auth.singup.domain

import com.saibabui.spendit.auth.singup.domain.ValidationResult
import java.util.regex.Pattern

class ValidatePassword {
    private val passwordPatternRegex =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    private val passwordPattern: Pattern
        get() = Pattern.compile(passwordPatternRegex)

    fun execute(password: String): ValidationResult {
        return if (password.length <= 7) {
            ValidationResult(
                isSuccessful = false,
                errorMessage = "Password should be grater than 7 characters"
            )
        } else if (!passwordPattern.matcher(password).matches()) {
            ValidationResult(
                isSuccessful = false,
                errorMessage = "Entered Password should contain least one number,special and upper case "
            )
        } else {
            ValidationResult(isSuccessful = true)
        }
    }
}