package com.vinh.retrofitcoroutinesoauth.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.vinh.retrofitcoroutinesoauth.R

class SessionManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token: String) {
//        val editor = prefs.edit()
//        editor.putString(USER_TOKEN, token)
//        editor.apply()

        // KTX
        prefs.edit {
            putString(USER_TOKEN, token)
        }
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

}