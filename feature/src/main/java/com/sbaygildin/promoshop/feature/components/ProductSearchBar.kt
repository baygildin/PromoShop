package com.sbaygildin.promoshop.feature.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sbaygildin.promoshop.core.logging.EventLogger
import com.sbaygildin.promoshop.core.R


@Composable
fun ProductSearchBar(modifier: Modifier = Modifier) {
    val text = remember { mutableStateOf("") }

    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
            EventLogger.logClick("Введен запрос товара: $it")
        },
        placeholder = {
            Text(
                text = stringResource(R.string.products_search),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        },
        textStyle = MaterialTheme.typography.labelSmall.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        singleLine = true,
        trailingIcon = {
            if (text.value.isNotEmpty()) {
                IconButton(onClick = {
                    text.value = ""
                    EventLogger.logClick("Очищен поиск товаров")
                }) {
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = stringResource(R.string.search),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            } else {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    )
}


