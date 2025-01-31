package com.sbaygildin.promoshop.feature.promo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbaygildin.promoshop.domain.model.AdressSuggestion
import com.sbaygildin.promoshop.domain.usecase.SearchAdressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PromoViewModel @Inject constructor(
    private val searchAdressUseCase: SearchAdressUseCase
) : ViewModel() {

    private val _adressSuggestions = MutableStateFlow<List<AdressSuggestion>>(emptyList())
    val adressSuggestions: StateFlow<List<AdressSuggestion>> = _adressSuggestions.asStateFlow()

    private val _selectedAdress = MutableStateFlow( "Выберите адрес")
    val selectedAdress: StateFlow<String> = _selectedAdress.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()


    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .filter { it.isNotEmpty() && it.length > 2 }
                .flatMapLatest { query ->
                    flow {
                        emit(searchAdressUseCase(query))
                    }.catch { emit(emptyList()) }
                }
                .collect { suggestions ->
                    _adressSuggestions.value = suggestions
                }
        }

    }
    fun onAdressSelected(adress: String) {
        _selectedAdress.value = adress
    }

    fun searchAdress(query: String) = viewModelScope.launch {
        _adressSuggestions.value = searchAdressUseCase(query)
    }

    fun onQueryChanged(query: String) {
        _searchQuery.value = query
    }

}

