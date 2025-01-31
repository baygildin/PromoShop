package com.sbaygildin.promoshop.feature.promo.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbaygildin.promoshop.core.R
import com.sbaygildin.promoshop.core.logging.EventLogger
import com.sbaygildin.promoshop.core.ui.SFUIText
import com.sbaygildin.promoshop.feature.promo.model.CatalogItem

@Composable
fun CatalogSections() {
    val categories = listOf(
        CatalogItem(stringResource(R.string.catalog_sets), R.drawable.ic_catalog_1, Color(0xFFFFC1B6)),
        CatalogItem(stringResource(R.string.catalog_pizza), R.drawable.ic_catalog_2, Color(0xFFFFE1AD)),
        CatalogItem(stringResource(R.string.catalog_pasta), R.drawable.ic_catalog_3, Color(0xFFBAB892)),
        CatalogItem(stringResource(R.string.catalog_risotto), R.drawable.ic_catalog_4, Color(0xFFC4D3CE)),
        CatalogItem(stringResource(R.string.catalog_salads), R.drawable.ic_catalog_5, Color(0xFFB9C4C8)),
        CatalogItem(stringResource(R.string.catalog_semi_finished), R.drawable.ic_catalog_6, Color(0xFFA3AE9D)),
        CatalogItem(stringResource(R.string.catalog_desserts), R.drawable.ic_catalog_7, Color(0xFFFFE6B6)),
        CatalogItem(stringResource(R.string.catalog_additions), R.drawable.ic_catalog_8, Color(0xFFD3C4C4)),
        CatalogItem(stringResource(R.string.catalog_drinks), R.drawable.ic_catalog_9, Color(0xFFFFD4AD))
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp, max = 500.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            contentPadding = PaddingValues(bottom = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { item ->
                Box(
                    modifier = Modifier
                        .size(108.dp, 140.dp)
                        .background(item.color, RoundedCornerShape(12.dp))
                        .clickable { EventLogger.logClick("Выбран каталог: \"${item.name}\"") }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(item.image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(96.dp)
                                .align(Alignment.CenterHorizontally),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = item.name,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 15.sp,
                                fontFamily = SFUIText,
                                fontWeight = FontWeight.W600,
                                color = Color.Black
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                    }
                }
            }
        }
    }
}