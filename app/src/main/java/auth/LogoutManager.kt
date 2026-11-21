package com.example.localeatsdiscovery.auth

import android.content.Context
import android.content.Intent
import com.example.localeatsdiscovery.login.LoginActivity

object LogoutManager {

    fun logout(context: Context) {

        // Clear login session
        SessionManager.logout(context)

        // Go back to Login screen
        val intent = Intent(context, LoginActivity::class.java)

        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK

        context.startActivity(intent)
    }
}