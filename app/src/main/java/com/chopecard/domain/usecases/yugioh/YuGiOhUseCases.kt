package com.chopecard.domain.usecases.yugioh

import com.chopecard.data.model.Card
import com.chopecard.data.model.CardSet
import com.chopecard.data.repository.CardRepository

class GetYugiohCardInfoByNameUseCase(private val repository: CardRepository) {
    suspend fun execute(cardName: String): List<Card> {
        return repository.getYugiohCardByName(cardName)
    }
}

class GetYugiohCardsInfoByTypeUseCase(private val repository: CardRepository) {
    suspend fun execute(cardType: String): List<Card> {
        return repository.getYugiohCardsByType(cardType)
    }
}

class GetYugiohCardSetInfoUseCase(private val repository: CardRepository) {
    suspend fun execute(setCode: String): List<CardSet> {
        return repository.getYugiohCardSetByCode(setCode)
    }
}
