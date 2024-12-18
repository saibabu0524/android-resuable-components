package com.saibabui.spendit.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.saibabui.spendit.auth.login.data.FirebaseLoginRepository
import com.saibabui.spendit.auth.singup.domain.ValidateEmail
import com.saibabui.spendit.auth.singup.domain.ValidatePassword
import com.saibabui.spendit.auth.singup.presentation.model.CustomTextFieldState
import com.saibabui.spendit.auth.singup.presentation.model.SignUpFormEvents
import com.saibabui.spendit.common.NetworkResult
import com.saibabui.spendit.common.PreferenceUtils
import com.saibabui.spendit.common.UserDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class LoginViewmodel @Inject constructor(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val firebaseLoginRepository: FirebaseLoginRepository,
    private val preferenceUtils: PreferenceUtils,
) : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth
    private val TAG = this::class.simpleName

    private var _passwordState = MutableStateFlow(CustomTextFieldState())

    val passwordState = _passwordState

    private var _emailState = MutableStateFlow(CustomTextFieldState())

    val emailState = _emailState

    var loginResponse = MutableStateFlow<NetworkResult<Boolean>>(NetworkResult.Success(false))
        private set


    fun validate(event: SignUpFormEvents) {
        when (event) {
            is SignUpFormEvents.EmailChangedEvent -> {
                val emailValidationResult = validateEmail.execute(event.email)
                emailState.value =
                    emailState.value.copy(
                        error = emailValidationResult.errorMessage,
                        value = event.email,
                        isValid = emailValidationResult.isSuccessful
                    )
            }

            is SignUpFormEvents.PasswordChangedEvent -> {
                val passwordValidationResult = validatePassword.execute(event.password)
                passwordState.value =
                    passwordState.value.copy(
                        error = passwordValidationResult.errorMessage,
                        value = event.password,
                        isValid = passwordValidationResult.isSuccessful
                    )
            }

            SignUpFormEvents.SignUpButtonEvent -> {
                if (emailState.value.isValid && passwordState.value.isValid) {
                    loginWithEmailAndPassword(emailState.value.value, passwordState.value.value)
                } else {
                    validateEmail.execute(emailState.value.value)
                    validatePassword.execute(passwordState.value.value)
                }
            }

            is SignUpFormEvents.RepeatedPasswordChangedEvent -> {}
            is SignUpFormEvents.NameChangedEvent -> {
            }
        }
    }

    private fun loginWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loginResponse.value = NetworkResult.Loading()
                val result = firebaseLoginRepository.loginWithEmailAndPassword(email, password)
                loginResponse.value = if (result.data == true) {
                    NetworkResult.Success(true)
                } else {
                    NetworkResult.Error(result.message)
                }
            }
        }
    }
    fun getCurrentUser() = Firebase.auth.currentUser
    fun adduserDetailsToPreferences(userDetails: UserDetails){
        preferenceUtils.userDetails= userDetails
    }
}