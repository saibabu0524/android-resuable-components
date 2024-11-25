package com.saibabui.spendit.common

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.saibabui.spendit.common.Constants.USER_DETAILS
import javax.inject.Inject

class PreferenceUtils @Inject constructor(application: Application) {
    private val preference: SharedPreferences =
        application.getSharedPreferences("aichatingapp", Context.MODE_PRIVATE)

    var userDetails: UserDetails?
        get() = try {
            Gson().fromJson(
                preference.getString(
                    USER_DETAILS, null
                ), UserDetails::class.java
            )
        } catch (e: JsonParseException) {
            null
        } catch (e: JsonSyntaxException) {
            null
        }
        set(value) = preference.edit().putString(USER_DETAILS, Gson().toJson(value)).apply()

}
