package com.example.plantwatering.presentation.screen.watering.components

import android.widget.Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.presentation.model.ui.theme.AlarmOffGray
import com.example.plantwatering.presentation.model.ui.theme.BoxGreen
import com.example.plantwatering.presentation.model.ui.theme.ButtonGreen
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.presentation.model.ui.theme.testFamily

@Composable
fun TabButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val underlineColor = if (selected) ButtonGreen else Color.Transparent

    Text(
        text = text,
        modifier = Modifier
            .padding(9.5.dp)
            .clickable { onClick() }
            .drawBehind{
                val stroke = 2.dp.toPx()
                val space = 45.dp.toPx()
                val bottomSpace =10.dp.toPx()
                drawLine(
                    color = underlineColor,
                    start = Offset(-space, size.height + bottomSpace),
                    end = Offset(size.width + space, size.height + bottomSpace),
                    strokeWidth = stroke
                )
            },
        color = if (selected) ButtonGreen else AlarmOffGray,
        fontSize = 18.sp
    )
}

@Preview(showBackground = true)
@Composable
fun TabButtonPre(){
    PlantWateringTheme{ TabButton("물 주기", true, onClick = {}) }
}
