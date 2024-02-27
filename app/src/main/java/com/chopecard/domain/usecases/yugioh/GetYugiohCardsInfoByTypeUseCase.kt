package com.chopecard.domain.usecases.yugioh

import com.chopecard.data.model.Card
import com.chopecard.data.repository.CardRepository

class GetYugiohCardsInfoByTypeUseCase(private val repository: CardRepository) {
    suspend fun execute(cardType: String): List<Card> {
        return repository.getYugiohCardsByType(cardType)
    }
}
