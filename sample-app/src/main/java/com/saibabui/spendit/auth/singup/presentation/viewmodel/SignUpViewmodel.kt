package com.saibabui.spendit.auth.singup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.saibabui.spendit.auth.FireBaseAuthRepository
import com.saibabui.spendit.auth.singup.domain.ValidateEmail
import com.saibabui.spendit.auth.singup.domain.ValidateName
import com.saibabui.spendit.auth.singup.domain.ValidatePassword
import com.saibabui.spendit.auth.singup.domain.ValidateRepeatedPassword
import com.saibabui.spendit.auth.singup.presentation.model.CustomTextFieldState
import com.saibabui.spendit.auth.singup.presentation.model.SignUpFormEvents
import com.saibabui.spendit.common.NetworkResult
import com.saibabui.spendit.common.PreferenceUtils
import com.saibabui.spendit.common.UserDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewmodel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val repeatedPassword: ValidateRepeatedPassword,
    private val authRepository: FireBaseAuthRepository,
    private val validateName: ValidateName,
) : ViewModel() {


    @Inject
    lateinit var preferenceUtils: PreferenceUtils

    private val TAG = this::class.simpleName

    private var currentUserId: String? = null

    private var _passwordState = MutableStateFlow(CustomTextFieldState())

    val passwordState = _passwordState

    private var _emailState = MutableStateFlow(CustomTextFieldState())

    val emailState = _emailState

    private var _repeatedPasswordState = MutableStateFlow(CustomTextFieldState())

    val repeatedPasswordState = _repeatedPasswordState

    private var _nameState = MutableStateFlow(CustomTextFieldState())

    val nameState = _nameState


    var signUpResponse = MutableStateFlow<NetworkResult<Boolean>>(NetworkResult.Success(false))
        private set

    private val _selectedLanguage = MutableStateFlow("en")
    val selectedLanguage: StateFlow<String> get() = _selectedLanguage

    fun updateLanguage(language: String) {
        viewModelScope.launch {
            _selectedLanguage.emit(language)
        }
    }

    fun validate(event: SignUpFormEvents) {
        when (event) {
            is SignUpFormEvents.EmailChangedEvent -> {
                val emailValidationResult = validateEmail.execute(event.email)
                _emailState.value =
                    _emailState.value.copy(
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

            is SignUpFormEvents.RepeatedPasswordChangedEvent -> {
                val repeatedPasswordValidationResult =
                    repeatedPassword.execute(
                        password = passwordState.value.value,
                        event.repeatedPassword
                    )
                repeatedPasswordState.value =
                    repeatedPasswordState.value.copy(
                        error = repeatedPasswordValidationResult.errorMessage,
                        value = event.repeatedPassword,
                        isValid = repeatedPasswordValidationResult.isSuccessful
                    )
            }

            SignUpFormEvents.SignUpButtonEvent -> {
                val emailValidationResult = validateEmail.execute(emailState.value.value)
                val passwordValidationResult = validatePassword.execute(passwordState.value.value)
                val nameResult = validateName.execute(nameState.value.value)
                val repeatedPasswordValidationResult =
                    repeatedPassword.execute(
                        password = passwordState.value.value,
                        repeatedPassword = repeatedPasswordState.value.value
                    )

                _emailState.value = _emailState.value.copy(
                    error = emailValidationResult.errorMessage,
                    isValid = emailValidationResult.isSuccessful
                )

                passwordState.value = passwordState.value.copy(
                    error = passwordValidationResult.errorMessage,
                    isValid = passwordValidationResult.isSuccessful
                )

                repeatedPasswordState.value = repeatedPasswordState.value.copy(
                    error = repeatedPasswordValidationResult.errorMessage,
                    isValid = repeatedPasswordValidationResult.isSuccessful
                )

                _nameState.value = _nameState.value.copy(
                    error = nameResult.errorMessage,
                    isValid = nameResult.isSuccessful
                )

                if (emailValidationResult.isSuccessful &&
                    passwordValidationResult.isSuccessful &&
                    repeatedPasswordValidationResult.isSuccessful && nameResult.isSuccessful
                ) {
                    createAccountWithEmailAndPassword(
                        emailState.value.value,
                        passwordState.value.value
                    )
                }
            }

            is SignUpFormEvents.NameChangedEvent -> {
                val nameResult = validateName.execute(event.name)
                _nameState.value =
                    _nameState.value.copy(
                        error = nameResult.errorMessage,
                        value = event.name,
                        isValid = nameResult.isSuccessful
                    )
            }
        }
    }

    private fun createAccountWithEmailAndPassword(
        email: String,
        password: String,
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                signUpResponse.value = NetworkResult.Loading()
                val result = authRepository.signUpWithEmailAndPassword(email, password)
                signUpResponse.value = if (result.data == true) {
                    currentUserId = FirebaseAuth.getInstance().currentUser?.uid
                    NetworkResult.Success(true)
                } else {
                    NetworkResult.Error(result.message)
                }
            }
        }
    }


    fun adduserDetailsToPreferences(userDetails: UserDetails){
        preferenceUtils.userDetails= userDetails
    }

    fun getCurrentUserId() = FirebaseAuth.getInstance().currentUser?.uid

}