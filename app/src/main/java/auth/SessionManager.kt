package com.example.localeatsdiscovery.auth

import android.content.Context

object SessionManager {

    private const val PREF_NAME = "local_eats_session"
    private const val KEY_LOGGED_IN = "logged_in"

    fun setLoggedIn(context: Context, loggedIn: Boolean) {
        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
            .edit()
            .putBoolean(KEY_LOGGED_IN, loggedIn)
            .apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        return context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
            .getBoolean(KEY_LOGGED_IN, false)
    }

    fun logout(context: Context) {
        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )
            .edit()
            .putBoolean(KEY_LOGGED_IN, false)
            .apply()
    }
}