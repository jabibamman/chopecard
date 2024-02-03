package com.chopecard.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.chopecard.MainActivity
import com.chopecard.data.storage.UserPreferences
import com.chopecard.presentation.viewModel.LoginViewModel
import injectModuleDependencies
import org.koin.androidx.viewmodel.ext.android.viewModel
import parseAndInjectConfiguration

class SplashActivity : BaseActivity() {

    private val loginViewModel : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        parseAndInjectConfiguration()
        injectModuleDependencies(this)
        Handler(Looper.getMainLooper()).postDelayed({
            decideNextActivity()
        }, 2000)
        decideNextActivity()
    }

    private fun decideNextActivity() {
        val userLogin = UserPreferences.getUserLogin(this)
        if (userLogin.first && userLogin.second > 0) {
            loginViewModel.getUser(userLogin.second) { user ->
                if (user != null) {
                    if (user.userId > 0) {
                        Log.d("SplashActivity", "Welcome back, ${user.name}")
                        navigateTo(MainActivity::class.java)
                    } else {
                        Log.e("SplashActivity", "Error getting user")
                        navigateTo(LoginRegisterActivity::class.java)
                    }
                } else {
                    Log.e("SplashActivity", "User not found")
                    UserPreferences.clearUserLogin(this)
                    navigateTo(LoginRegisterActivity::class.java)
                }
            }
        } else {
            Log.d("SplashActivity", "User not logged in")
            navigateTo(LoginRegisterActivity::class.java)
        }
    }

    private fun navigateTo(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
        finish()
    }
}
