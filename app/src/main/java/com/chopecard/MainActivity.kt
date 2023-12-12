package com.chopecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.chopecard.data.model.Card
import com.chopecard.data.repository.CardRepository
import com.chopecard.databinding.ActivityMainBinding
import injectModuleDependencies
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import parseAndInjectConfiguration

class MainActivity : ComponentActivity() {
    val cardRepository: CardRepository by inject()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        // view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        parseAndInjectConfiguration()
        injectModuleDependencies(this)

        loadCardInfo("Tornado Dragon")
    }

    fun loadCardInfo(cardName: String) {
        lifecycleScope.launch {
            val cardInfo : List<Card> = cardRepository.getCardByName(cardName)
            val imageViewCard = binding.bodyLayout.imageViewCard

            binding.bodyLayout.textViewCardName.text = cardInfo[0].name
            binding.bodyLayout.textViewCardType.text = cardInfo[0].type

            // on charge l'image depuis l'URL avec glide
            Glide.with(this@MainActivity)
                .load(cardInfo[0].card_images[0].image_url)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(imageViewCard)
        }
    }
}


