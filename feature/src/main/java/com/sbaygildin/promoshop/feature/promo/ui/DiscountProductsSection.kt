package com.sbaygildin.promoshop.feature.promo.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sbaygildin.promoshop.core.R
import com.sbaygildin.promoshop.feature.promo.model.ProductItem


@Composable
fun DiscountProductsSection() {
    val promotionItems = listOf(

        ProductItem(
            "Черные спагетти с морепродуктами (большая порция)",
            R.drawable.ic_discount_1,
            "230г",
            "360 ₽",
            null,
            "-35%",
            true
        ),
        ProductItem(
            "Казаречче с индейкой и томатами",
            R.drawable.ic_discount_2,
            "230г",
            "200 ₽",
            "360 ₽",
            "-25%",
            false
        ),
        ProductItem(
            "Равиоли с грибами",
            R.drawable.ic_discount_3,
            "230г",
            "1200 ₽",
            "1360 ₽",
            "-45%",
            true
        ),
        ProductItem(
            "Черные спагетти с морепродуктам (большая порция)",
            R.drawable.ic_discount_1,
            "230г",
            "360 ₽",
            null,
            "-35%",
            false
        ),
        ProductItem(
            "Казаречче с индейкой и томатами",
            R.drawable.ic_discount_2,
            "230г",
            "200 ₽",
            "360 ₽",
            "-25%",
            true
        ),
        ProductItem(
            "Равиоли с грибами",
            R.drawable.ic_discount_3,
            "230г",
            "1200 ₽",
            "1360 ₽",
            "-35%",
            true
        ),
        ProductItem(
            "Равиоли с грибами",
            R.drawable.ic_discount_1,
            "230г",
            "1200 ₽",
            "1360 ₽",
            "-35%",
            true
        ),
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(promotionItems) { it ->

            PromotionItemCard(it)
        }
    }
}