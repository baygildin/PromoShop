package com.sbaygildin.promoshop.feature.promo.model

data class ProductItem(
    val name: String,
    val image: Int,
    val weight: String,
    val price: String,
    val oldPrice: String?,
    val discount: String,
    val isNew: Boolean = false,
)
