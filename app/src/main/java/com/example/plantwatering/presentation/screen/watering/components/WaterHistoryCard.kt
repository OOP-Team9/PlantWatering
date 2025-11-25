package com.example.plantwatering.presentation.screen.watering.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.R
import com.example.plantwatering.presentation.model.ui.theme.AlarmOffGray
import com.example.plantwatering.presentation.model.ui.theme.testFamily

@Composable
fun WaterHistoryCard(
    plant: Plant
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            //.padding( start = 14.dp, bottom = 8.dp, end = 14.dp)
            .padding( horizontal = 14.dp, vertical = 5.dp)
    ) {
        Column(){
            Row(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.history_water),
                    contentDescription = null,
                    modifier = Modifier
                        .size(44.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = plant.name,
                        fontSize = 17.sp,
                        fontFamily = testFamily
                    )

                    // 업데이트 시간으로부터.. 시간 계산
                    var time:Int = 3
                    var unit:String = "시간"
                    Text(
                        text = "${time} ${unit} 전",
                        fontSize = 15.sp,
                        fontFamily = testFamily,
                        color = AlarmOffGray
                    )
                }
            }
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WaterHistoryPre() {
    WaterHistoryCard(
        plants[2]
    )
}