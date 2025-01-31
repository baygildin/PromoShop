package com.sbaygildin.promoshop.feature.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbaygildin.promoshop.core.R
import com.sbaygildin.promoshop.core.ui.SFUIText

@Composable
fun DropDownAdressList(
    selectedAddress: State<String>,
    onAddressClick: () -> Unit
) {
    Column {
        Text(
            text = stringResource(R.string.delivery),
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontFamily = SFUIText,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF717171)
            )
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = selectedAddress.value,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = SFUIText,
                    fontWeight = FontWeight.Medium,
                    color = Color( 0xff000000)
                ),
                modifier = Modifier.clickable(onClick = onAddressClick)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_drop_down),
                contentDescription = stringResource(R.string.change_address),
                tint = Color.Gray,
                modifier = Modifier
                    .size(12.dp)
                    .clickable(onClick = onAddressClick)
            )
        }
    }
}