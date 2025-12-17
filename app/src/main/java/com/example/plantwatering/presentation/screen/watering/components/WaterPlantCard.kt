package com.example.plantwatering.presentation.screen.watering.components

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.border
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.R
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.presentation.model.ui.theme.StrokeGray
import com.example.plantwatering.presentation.model.ui.theme.White
import com.example.plantwatering.presentation.model.ui.theme.dropShadow
import com.example.plantwatering.presentation.model.ui.theme.testFamily
import com.example.plantwatering.presentation.model.ui.theme.ButtonGreen
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale

@Composable
fun WaterPlantCard(
    plant: PlantUi,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val fmt = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    val todayStr = fmt.format(Date())

    val nextStr = fmt.format(Date(plant.nextWateringAtEpoch))
    val lastStr = fmt.format(Date(plant.lastWateredAtEpoch))

    val status = when {
        (nextStr != todayStr)
                && (lastStr == todayStr)
                && plant.wateringStatus -> 1 // 파랑
        (nextStr != todayStr) -> 2 // 회색
        (nextStr == todayStr) && !plant.wateringStatus -> 0 // 경고
        (nextStr == todayStr) && plant.wateringStatus -> 1 // 파랑
        else -> 2 // 회색
    }

    // D-Day 계산
    val nextDate = Instant.ofEpochMilli(plant.nextWateringAtEpoch)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    val todayDate = LocalDate.now()

    val days = ChronoUnit.DAYS.between(todayDate, nextDate).toInt()

    val dDayText = when {
        days == 0 -> "D-DAY"
        days > 0 -> "D-$days"
        else -> ""
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding( start = 14.dp, bottom = 8.dp, end = 14.dp)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) ButtonGreen else Color.Transparent,
                shape = RoundedCornerShape(10)
            )
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
                        fontSize = 17.sp
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
                    text = "물 주기: ${plant.wateringIntervalDays}일",
                    fontSize = 15.sp,
                    color = StrokeGray
                )

                Text(
                    text = "다음 급수 $dDayText",
                    fontSize = 15.sp,
                    color = Color(0xFF0084FF)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WaterPlantCardPre() {
    PlantWateringTheme {
        WaterPlantCard(
            plants[2], onClick = {}
        )
    }
}