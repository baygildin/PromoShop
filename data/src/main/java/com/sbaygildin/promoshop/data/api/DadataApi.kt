package com.sbaygildin.promoshop.data.api

import com.sbaygildin.promoshop.data.api.dto.AdressSuggestionsResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface DadataApi {
    @POST("suggestions/api/4_1/rs/suggest/address")
    suspend fun suggestAdress(
        @Body request: AdressRequest,
    ): AdressSuggestionsResponse
}


data class AdressRequest(
    val query: String,
    val count: Int = 10,
)