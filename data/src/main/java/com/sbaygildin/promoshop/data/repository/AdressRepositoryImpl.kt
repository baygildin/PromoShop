package com.sbaygildin.promoshop.data.repository

import com.sbaygildin.promoshop.data.api.AdressRequest
import com.sbaygildin.promoshop.data.api.DadataApi
import com.sbaygildin.promoshop.data.api.dto.toDomain
import com.sbaygildin.promoshop.domain.model.AdressSuggestion
import com.sbaygildin.promoshop.domain.repository.AdressRepository
import javax.inject.Inject

class AdressRepositoryImpl @Inject constructor(
    private val api: DadataApi
) : AdressRepository {
    override suspend fun searchAdress(query: String): List<AdressSuggestion>{
        return api.suggestAdress(AdressRequest(query)).suggestions
            .map { it.toDomain()}
    }
}

