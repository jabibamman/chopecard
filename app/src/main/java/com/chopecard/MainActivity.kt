package com.chopecard

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.chopecard.data.repository.CardRepository
import com.chopecard.databinding.ActivityMainBinding
import com.chopecard.presentation.viewModel.CardViewModel
import com.chopecard.ui.activity.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    val cardRepository: CardRepository by inject()
    private lateinit var binding: ActivityMainBinding
    private val cardViewModel: CardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFooter()

    }

}
