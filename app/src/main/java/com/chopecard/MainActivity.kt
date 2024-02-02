package com.chopecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatDelegate
import com.chopecard.data.repository.CardRepository
import com.chopecard.databinding.ActivityMainBinding
import com.chopecard.presentation.viewModel.CardViewModel
import injectModuleDependencies
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import parseAndInjectConfiguration

class MainActivity : ComponentActivity() {
    val cardRepository: CardRepository by inject()
    private lateinit var binding: ActivityMainBinding
    private val cardViewModel: CardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        // view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        parseAndInjectConfiguration()
        injectModuleDependencies(this)

        /*cardViewModel.cardInfoLiveData.observe(this) { cardUIModel ->
            displayCardInfo(cardUIModel)
        }

         */

        //cardViewModel.loadCardInfo("Dark Magician Girl")
    //    cardViewModel.loadCardInfo("United We Stand")
    }

}
