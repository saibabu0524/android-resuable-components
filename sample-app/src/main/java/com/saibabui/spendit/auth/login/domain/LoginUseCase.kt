package com.saibabui.spendit.auth.login.domain

import com.saibabui.spendit.auth.login.data.FirebaseLoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    val firebaseLoginRepository: FirebaseLoginRepository
) {
    fun execute(){

    }
}