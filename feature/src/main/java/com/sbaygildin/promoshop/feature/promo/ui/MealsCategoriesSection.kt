package com.sbaygildin.promoshop.feature.promo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbaygildin.promoshop.core.R
import com.sbaygildin.promoshop.core.logging.EventLogger


@Composable
fun MealsCategoriesSection() {

    val categories = listOf(
        stringResource(R.string.meal_summer_picnic) to R.drawable.ic_meals_1,
        stringResource(R.string.meal_summer_lunch) to R.drawable.ic_meals_2,
        stringResource(R.string.meal_breakfast) to R.drawable.ic_meals_3,
        stringResource(R.string.meal_dinner) to R.drawable.ic_meals_4,
        stringResource(R.string.meal_lunch) to R.drawable.ic_meals_3,
        stringResource(R.string.meal_brunch) to R.drawable.ic_meals_2,
        stringResource(R.string.meal_snack) to R.drawable.ic_meals_1,
    )
    LazyRow(
        modifier = Modifier.padding(top = 16.dp),
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        items(categories) { (name, image) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(64.dp)
                    .clickable {
                        EventLogger.logClick("Выбрана категория: $name")
                    }
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp, 53.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Text(
                    text = name,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
