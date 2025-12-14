package com.example.plantwatering.presentation.screen.alarm.components

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.presentation.model.ui.theme.testFamily
import com.example.plantwatering.presentation.screen.watering.components.Plant
import com.example.plantwatering.presentation.screen.watering.components.plants

@Composable
fun AlarmInfoBox(){
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(start = 20.dp, top = 110.dp, bottom = 40.dp, end = 20.dp)
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)

        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "알람으로 물주기 \n" +
                            "놓치지 마세요 \uD83D\uDCA6",
                    fontSize = 25.sp,
                    lineHeight = 35.sp
                )
                AddCircleButton { onClick }
            }

            Row(){
                Text(
                    text = "설정한 시간에 급수일인 식물 이름과 함께 알람을 보낼게요!",
                    fontSize = 14.sp,
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun AlarmInfoBoxPre() {
    PlantWateringTheme {
        AlarmInfoBox()
    }
}
