package com.chopecard

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.chopecard.data.storage.UserPreferences
import com.chopecard.databinding.ActivityMainBinding
import com.chopecard.ui.activity.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFooter()

        binding.tvWelcome.text = getString(R.string.welcome, UserPreferences.getUserName(this))
    }
}
