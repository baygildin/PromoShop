package com.sbaygildin.promoshop.domain.usecase

import com.sbaygildin.promoshop.domain.model.AdressSuggestion
import com.sbaygildin.promoshop.domain.repository.AdressRepository


class SearchAdressUseCase(
    private val repository: AdressRepository
) {
    suspend operator fun invoke(query: String): List<AdressSuggestion> {
        return repository.searchAdress(query)
    }
}

