package com.chopecard.data.storage

import android.content.Context

object UserPreferences {
    private const val PREFERENCES_FILE_KEY = "com.chopecard.userprefs"
    private const val IS_LOGGED_IN_KEY = "isLoggedIn"
    private const val SHOW_WELCOME_KEY = "showWelcome"

    fun saveUserLogin(context: Context, userId: Int) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean(IS_LOGGED_IN_KEY, true)
            putInt("userId", userId)
            apply()
        }
    }

    fun getUserLogin(context: Context): Pair<Boolean, Int> {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean(IS_LOGGED_IN_KEY, false)
        val userId = sharedPreferences.getInt("userId", -1) // -1 (default value if userId not found)
        return isLoggedIn to userId
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
            putBoolean(IS_LOGGED_IN_KEY, false)
            putInt("userId", -1)
            apply()
        }

    }

}
