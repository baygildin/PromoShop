@file:OptIn(ExperimentalMaterial3Api::class)

package com.sbaygildin.promoshop.feature.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sbaygildin.promoshop.core.R
import com.sbaygildin.promoshop.domain.model.AdressSuggestion
import com.sbaygildin.promoshop.feature.promo.PromoViewModel

@Composable
fun AddressSearchModal(
    viewModel: PromoViewModel,
    onDismiss: () -> Unit
) {
    val queryState = rememberTextFieldState()
    var expanded by rememberSaveable { mutableStateOf(true) }
    val currentLocationSuggestion = AdressSuggestion(
        value = stringResource(R.string.current_location),
        unrestrictedValue = stringResource(R.string.your_current_location),
        region = stringResource(R.string.geolocation)
    )
    val suggestions =
        listOf(currentLocationSuggestion) + viewModel.adressSuggestions.collectAsState().value

    LaunchedEffect(queryState.text) {
        viewModel.onQueryChanged(queryState.text.toString())
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        scrimColor = Color(0x80000000),
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                inputField = {
                    SearchBarDefaults.InputField(
                        query  = queryState.text.toString(),
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        onSearch = { expanded = false },
                        placeholder = { Text(stringResource(R.string.search_address)) },
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = stringResource(R.string.search))
                        },
                        trailingIcon = {
                            if (queryState.text.isNotEmpty()) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = stringResource(R.string.clear),
                                    modifier = Modifier.clickable {
                                        queryState.setTextAndPlaceCursorAtEnd("")
                                    }
                                )
                            }
                        },
                        onQueryChange = { query ->
                            queryState.setTextAndPlaceCursorAtEnd(query)
                            viewModel.onQueryChanged(query)
                        },
                    )
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                shape = RoundedCornerShape(8.dp),
                colors = SearchBarDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface,
                )
            ) {
                LazyColumn {
                    items(suggestions) { suggestion ->
                        ListItem(
                            headlineContent = { Text(suggestion.value) },
                            modifier = Modifier
                                .clickable {
                                    viewModel.onAdressSelected(suggestion.value)
                                    onDismiss()
                                }
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            leadingContent = {
                                Icon(
                                    painter = painterResource(
                                        id = if (suggestion.value == stringResource(R.string.current_location))
                                            R.drawable.location_near
                                        else
                                            R.drawable.location_on
                                    ),
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
