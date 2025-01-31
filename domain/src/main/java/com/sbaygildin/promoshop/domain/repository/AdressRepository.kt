package com.sbaygildin.promoshop.domain.repository

import com.sbaygildin.promoshop.domain.model.AdressSuggestion

interface AdressRepository {
    suspend fun searchAdress(query: String): List<AdressSuggestion>
}

