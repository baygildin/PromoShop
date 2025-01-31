package com.sbaygildin.promoshop.feature.promo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbaygildin.promoshop.core.logging.EventLogger
import com.sbaygildin.promoshop.core.ui.SFUIText
import com.sbaygildin.promoshop.core.R
import com.sbaygildin.promoshop.feature.promo.model.ProductItem

@Composable
fun PromotionItemCard(item: ProductItem) {
    var quantity by remember { mutableStateOf(0) }

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F4F4)),
        modifier = Modifier
            .padding(8.dp)
            .width(102.dp)
            .height(208.dp)
            .clickable {
                EventLogger.logClick("Нажат акционный товар: ${item.name}")
            }
    ) {
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Box {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFF4231), RoundedCornerShape(10.dp))
                            .height(15.dp)
                            .padding(horizontal = 4.dp)
                    ) {
                        Text(
                            text = item.discount,
                            style = TextStyle(
                                fontSize = 10.sp,
                                lineHeight = 15.sp,
                                fontFamily = SFUIText,
                                fontWeight = FontWeight.Normal,
                                color = Color.White
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    if (item.isNew) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFFFA033), RoundedCornerShape(10.dp))
                                .width(49.dp)
                                .height(15.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.new_thing),
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    lineHeight = 15.sp,
                                    fontFamily = SFUIText,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White
                                ),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }

                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = item.name,
                    modifier = Modifier
                        .size(102.dp, 93.dp)
                        .padding(top = 6.dp)
                )
            }
            Text(
                text = item.name,
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 13.sp,
                    fontFamily = SFUIText,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                ),
                textAlign = TextAlign.Start,
                maxLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp)
            )

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = item.weight,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(4.dp))
                if (quantity > 0) {
                    Text(
                        text = item.price,
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            fontFamily = SFUIText,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF717171)
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (quantity > 0) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFFD334), RoundedCornerShape(28.dp))
                            .width(92.dp)
                            .height(29.dp)
                            .padding(bottom = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .background(Color(0xFFFFD334), RoundedCornerShape(50))
                                    .clickable {
                                        if (quantity > 0) quantity--
                                        EventLogger.logClick("Убавлено из корзины единица товара: ${item.name}. Итого: $quantity")
                                    }
                            ) {
                                Text(
                                    text = "-",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }

                            Text(
                                text = quantity.toString(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )

                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .background(Color(0xFFFFD334), RoundedCornerShape(50))
                                    .clickable { quantity++ }
                            ) {
                                Text(
                                    text = "+",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            if (item.oldPrice != null) {
                                Text(
                                    text = item.oldPrice,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        lineHeight = 20.sp,
                                        fontFamily = SFUIText,
                                        fontWeight = FontWeight.Normal,
                                        color = Color(0xFFFF4231)
                                    ),
                                    textDecoration = TextDecoration.LineThrough
                                )
                            }
                            Text(
                                text = item.price,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 20.sp,
                                    fontFamily = SFUIText,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .background(Color(0xFFFFD334), RoundedCornerShape(50))
                                .clickable {
                                    quantity++
                                    EventLogger.logClick("Добавлено в корзину единица товара: ${item.name}. Итого: $quantity")
                                }
                        ) {
                            Text(
                                text = "+",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}
