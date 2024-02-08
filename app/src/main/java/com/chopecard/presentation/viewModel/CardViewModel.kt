package com.chopecard.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chopecard.data.model.Card
import com.chopecard.data.model.CardUIModel
import com.chopecard.data.repository.CardRepository
import com.chopecard.domain.usecases.GetProductDetailUseCase
import kotlinx.coroutines.launch

class CardViewModel(private val cardRepository: CardRepository) : ViewModel() {
    private val cardInfoLiveData = MutableLiveData<CardUIModel>()
    fun loadCardInfo(cardName: String) {
        viewModelScope.launch {
            try {
                val cards = GetProductDetailUseCase(cardRepository).execute(cardName)
                cards[0].let {
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