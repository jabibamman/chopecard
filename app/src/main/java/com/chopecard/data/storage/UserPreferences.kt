package com.chopecard.data.storage

import android.content.Context
import com.chopecard.domain.models.ERole

object UserPreferences {
    private const val PREFERENCES_FILE_KEY = "com.chopecard.userprefs"
    private const val IS_LOGGED_IN_KEY = "isLoggedIn"
    private const val SHOW_WELCOME_KEY = "showWelcome"

    fun saveUserLogin(context: Context, userId: Int, userName: String, userRole:String) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("userRole", userRole)
            putInt("userId", userId)
            putString("userName", userName)
            apply()
        }
    }

    fun getUserLogin(context: Context): Triple<String?, Int, String> {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        val userRole = sharedPreferences.getString("userRole", ERole.USER.name) // "USER" (default value if userRole not found)
        val userId = sharedPreferences.getInt("userId", -1) // -1 (default value if userId not found)
        val userName = sharedPreferences.getString("userName", "") // "" (default value if userName not found)
        return Triple(userRole, userId, userName!!)
    }

    fun getUserName(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString("userName", "")!!
    }

    fun setWelcomeShown(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean(SHOW_WELCOME_KEY, false)
            apply()
        }
    }

    fun shouldShowWelcome(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(SHOW_WELCOME_KEY, true)
    }

    fun clearUserLogin(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt("userId", -1)
            apply()
        }
    }

    fun clearAllPreference(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("userRole",  ERole.USER.name)
            putInt("userId", -1)
            putString("userName", "")

            apply()
        }
    }

}
