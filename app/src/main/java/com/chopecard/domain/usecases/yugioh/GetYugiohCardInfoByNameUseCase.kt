package com.chopecard.domain.usecases.yugioh

import com.chopecard.data.model.Card
import com.chopecard.data.repository.CardRepository

class GetYugiohCardInfoByNameUseCase(private val repository: CardRepository) {
    suspend fun execute(cardName: String): List<Card> {
        return repository.getYugiohCardByName(cardName)
    }
}
