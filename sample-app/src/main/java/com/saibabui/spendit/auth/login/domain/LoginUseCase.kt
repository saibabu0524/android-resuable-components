package com.saibabui.spendit.auth.login.domain

import com.saibabui.spendit.auth.login.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    val loginRepository: LoginRepository
) {
    fun execute(){

    }
}