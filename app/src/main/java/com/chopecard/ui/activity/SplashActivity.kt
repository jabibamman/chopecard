package com.chopecard.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.chopecard.MainActivity
import com.chopecard.data.storage.UserPreferences
import com.chopecard.di.injectModuleDependencies
import com.chopecard.di.parseAndInjectConfiguration
import com.chopecard.presentation.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    private val loginViewModel : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        parseAndInjectConfiguration()
        injectModuleDependencies(this)
        decideNextActivity()
        decideNextActivity()
    }

    private fun decideNextActivity() {
        val userLogin = UserPreferences.getUserLogin(this)
        if (userLogin.second > 0) {
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
