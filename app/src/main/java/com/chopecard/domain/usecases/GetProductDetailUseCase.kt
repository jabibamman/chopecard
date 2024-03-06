// GetProductDetailUseCase.kt
package com.chopecard.domain.usecases

import com.chopecard.data.model.Card
import com.chopecard.data.repository.CardRepository

class GetProductDetailUseCase(private val cardRepository: CardRepository) {
    suspend fun execute(cardName: String): List<Card> {
        if (cardName.isEmpty()) {
            throw IllegalArgumentException("Card name cannot be empty")
        }
        return cardRepository.getYugiohCardByName(cardName)
    }
}
