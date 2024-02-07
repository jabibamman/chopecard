package com.chopecard.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.data.model.Card
import com.chopecard.data.model.CardUIModel
import com.chopecard.data.repository.CardRepository
import kotlinx.coroutines.launch

class CardViewModel(private val cardRepository: CardRepository) : ViewModel() {
    val cardInfoLiveData = MutableLiveData<CardUIModel>()
    fun loadCardInfo(cardName: String) {
        viewModelScope.launch {
            try {
                val card = cardRepository.getYugiohCardByName(cardName)
                card[0].let {
                    cardInfoLiveData.postValue(mapToCardUIModel(it))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun mapToCardUIModel(card: Card): CardUIModel {
        return CardUIModel(
            name = card.name,
            type = card.type,
            rarity = card.card_sets.firstOrNull()?.set_rarity.orEmpty(),
            price = "$ ${card.card_prices.firstOrNull()?.ebay_price}",
            imageUrl = card.card_images.firstOrNull()?.image_url.orEmpty(),
            description = card.desc
        )
    }
}
