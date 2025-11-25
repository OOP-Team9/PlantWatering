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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.R
import com.example.plantwatering.presentation.model.ui.theme.StrokeGray
import com.example.plantwatering.presentation.model.ui.theme.White
import com.example.plantwatering.presentation.model.ui.theme.dropShadow
import com.example.plantwatering.presentation.model.ui.theme.testFamily
import java.util.Date

@Composable
fun WaterPlantCard(
    plant: Plant,
    onClick: () -> Unit
) {
    //다음 급수 날과 오늘이 같고, 급수 상태가 false일 경우 -> 위험, true -> 물방울 파랑, else -> 회색 물방울
    // 상태 계산
//    val status: Int = when {
//        //plant.nextWateringDate != Date() -> 0 // 현재는 하드코딩
//        // 깔끔하게 고치기
//        plant.nextWateringDate == "2025.11.29" && plant.wateringStatus == false -> 0
//        plant.nextWateringDate == "2025.11.29" && plant.wateringStatus == true -> 1
//        else -> 2
//    }
    val status: Int = if(plant.nextWateringDate == "2025.11.29"){
        if(plant.wateringStatus == false) 0
        else  1
    }else 2


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding( start = 14.dp, bottom = 8.dp, end = 14.dp)
            .clickable { onClick() }
            .dropShadow(shape = RoundedCornerShape(10), blur = 2.dp)
            .background(shape = RoundedCornerShape(10), color = White)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .width(351.dp)
        ) {
            // 이미지 : 더미데이터
            Image(
                painter = painterResource(id = R.drawable.mon),
                contentDescription = null,
                modifier = Modifier
                    .height(79.dp)
                    .width(70.dp)
            )

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = plant.name,
                        fontSize = 17.sp,
                        fontFamily = testFamily
                    )

                    val icon = when (status) {
                        0 -> R.drawable.ic_warning
                        1 -> R.drawable.ic_water_blue
                        else -> R.drawable.ic_water_gray
                    }

                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                    )
                }
                Text(
                    text = "물 주기: ${plant.wateringInterval}일",
                    fontSize = 15.sp,
                    fontFamily = testFamily,
                    color = StrokeGray
                )
                // 다음 급수 계산
                // (마지막 급수일 + 주기) - (오늘 날짜)
                Text(
                    text = "다음 급수 D-DAY",
                    fontSize = 15.sp,
                    fontFamily = testFamily,
                    color = Color(0xFF0084FF)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WaterPlantCardPre() {
    WaterPlantCard(
        plants[2], onClick = {}
    )
}