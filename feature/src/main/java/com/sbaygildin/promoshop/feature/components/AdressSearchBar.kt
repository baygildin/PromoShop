@file:OptIn(ExperimentalMaterial3Api::class)

package com.sbaygildin.promoshop.feature.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sbaygildin.promoshop.core.R
import com.sbaygildin.promoshop.core.logging.EventLogger
import com.sbaygildin.promoshop.domain.model.AdressSuggestion
import com.sbaygildin.promoshop.feature.promo.PromoViewModel


@Composable
fun AdressSearchBar(
    viewModel: PromoViewModel,
) {

    val queryState = rememberTextFieldState()
    var expanded by rememberSaveable { mutableStateOf(false) }
    val currentLocationSuggestion = AdressSuggestion(
        value = "Текущее местоположение",
        unrestrictedValue = "Ваше текущее местоположение",
        region = "Геолокация"
    )
    val suggestions =
        listOf(currentLocationSuggestion) + viewModel.adressSuggestions.collectAsState().value

    LaunchedEffect(queryState.text) {
        viewModel.onQueryChanged(queryState.text.toString())
        EventLogger.logClick("Изменен запрос: ${queryState.text}")
    }
    Box(Modifier
        .width(335.dp)
        .height(35.dp)
        .semantics { isTraversalGroup = true }) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(

                    query = queryState.text.toString(),
                    expanded = expanded,
                    onSearch = { expanded = false },
                    onQueryChange = { query ->
                        queryState.setTextAndPlaceCursorAtEnd(query)
                        viewModel.onQueryChanged(query)
                        EventLogger.logClick("Запрос обновлен: $query")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp)),
                    placeholder = { Text("Введите адрес") },
                    onExpandedChange = { expanded = it },
                    trailingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Поиск")
                    },
                )
            },
            expanded = expanded,
            onExpandedChange = {
                expanded = it
            },
        ) {
            LazyColumn {
                items(suggestions) { suggestion ->
                    ListItem(
                        headlineContent = { Text(suggestion.value) },
                        modifier = Modifier
                            .clickable {
                                queryState.setTextAndPlaceCursorAtEnd(suggestion.value)
                                viewModel.onAdressSelected(suggestion.value)
                                expanded = false
                                EventLogger.logClick("Выбран адрес: ${suggestion.value}")
                            }
                            .fillMaxWidth(),
                        leadingContent = {
                            Icon(
                                painter = painterResource(
                                    id = if (suggestion.value == "Текущее местоположение")
                                        R.drawable.location_near
                                    else
                                        R.drawable.location_on
                                ), contentDescription = null
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdressSearchBarPreview() {
    val viewModel = hiltViewModel<PromoViewModel>()
    AdressSearchBar(viewModel = viewModel)
}