package com.chopecard.domain.usecases.yugioh

import com.chopecard.data.model.CardSet
import com.chopecard.data.repository.CardRepository

class GetYugiohCardSetInfoUseCase(private val repository: CardRepository) {
    suspend fun execute(setCode: String): List<CardSet> {
        return repository.getYugiohCardSetByCode(setCode)
    }
}
