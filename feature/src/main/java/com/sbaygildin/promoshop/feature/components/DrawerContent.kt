package com.sbaygildin.promoshop.feature.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbaygildin.promoshop.core.R
import com.sbaygildin.promoshop.core.logging.EventLogger
import com.sbaygildin.promoshop.core.ui.SFUIText


@Composable
fun DrawerContent(onItemClick: (String) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column {
            Spacer(modifier = Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_face),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(32.dp))
                Column {
                    Text(
                        text = "Иван Иванов",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontFamily = SFUIText,
                            fontWeight = FontWeight.W500,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "+7 996 198-74-95",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontFamily = SFUIText,
                            fontWeight = FontWeight.W500,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()

            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { EventLogger.logClick("Нажата кнопка оплаты")}) {
                Column(modifier = Modifier.padding(0.dp)) {
                    Text(
                        text = "Оплата",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontFamily = SFUIText,
                            fontWeight = FontWeight.W500,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Карта *4567",
                        modifier = Modifier.padding(bottom = 24.dp),
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontFamily = SFUIText,
                            fontWeight = FontWeight.W400,
                            color = Color(0xFF717171)
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }

            val menuItems = listOf(
                "Мои адреса", "Мои заказы",
                "Избранное", "Новости", "Купоны", "О нас", "Пригласить друзей", "Настройки"
            )
            menuItems.forEach { item ->
                TextButton(onClick = {
                    EventLogger.logClick("Выбрано позиция меню: $item")
                    onItemClick(item) }) {
                    Text(
                        modifier = Modifier.padding(bottom = 24.dp),
                        text = item,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            fontFamily = SFUIText,
                            fontWeight = FontWeight.W500,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
                    .clickable {
                        EventLogger.logClick("Нажата кнопка Связаться с нами")
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_message),
                    contentDescription = "Message us",
                    modifier = Modifier
                        .size(52.dp)
                        .padding(start = 16.dp),
                    tint = Color(0xFF717171)
                )

                Text(
                    text = "Связаться с нами",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 15.sp,
                        fontFamily = SFUIText,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFF717171)
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DrawerContentPreview() {
    DrawerContent(onItemClick = {})
}