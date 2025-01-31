package com.sbaygildin.promoshop.data.api.dto

import com.google.gson.annotations.SerializedName
import com.sbaygildin.promoshop.domain.model.AdressSuggestion

data class AdressSuggestionsResponse(
    val suggestions: List<AdressSuggestionDTO>
)

data class AdressSuggestionDTO(
    val value: String,
    @SerializedName("unrestricted_value")
    val unrestrictedValue: String,
    val data: AdressData
)

fun AdressSuggestionDTO.toDomain(): AdressSuggestion{
    return AdressSuggestion(
        value = this.value,
        unrestrictedValue = this.unrestrictedValue,
        region = this.data.region
    )
}

data class AdressData(
    @SerializedName("postal_code")
    val postalCode: String?,
    @SerializedName("geo_lat")
    val geoLat: String?,
    @SerializedName("geo_lon")
    val geoLon: String?,
    @SerializedName("region_with_type")
    val region: String?
)