package com.sbaygildin.promoshop.feature.promo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbaygildin.promoshop.core.R
import com.sbaygildin.promoshop.core.ui.Roboto


@Composable
fun PromoBannersSection() {

    val categories = Array<Int>(7) { it ->
        if (it % 2 == 0) R.drawable.ic_promobanner_1
        else R.drawable.ic_promobanner_2
    }
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(categories) { image ->
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .size(290.dp, 115.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(5.dp)),
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 14.dp, bottom = 17.dp)
                    ) {
                        if (image == R.drawable.ic_promobanner_1) {

                            Text(
                                text = "В честь открытия",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    lineHeight = 25.sp,
                                    fontFamily = Roboto,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White
                                )
                            )
                            Text(
                                text = "Скидка 20%",
                                style = TextStyle(
                                    fontSize = 25.sp,
                                    lineHeight = 25.sp,
                                    fontFamily = Roboto,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                        }
                    }

                }


            }
        }
    }

}

