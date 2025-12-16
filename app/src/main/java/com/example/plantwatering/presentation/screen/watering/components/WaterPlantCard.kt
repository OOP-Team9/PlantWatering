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
import java.time.Instant
import java.time.ZoneId
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WaterPlantCard(
    plant: PlantUi,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val status = remember(plant.wateringStatus, plant.nextWateringAtEpoch, plant.lastWateredAtEpoch) {
        val nextDate = Instant.ofEpochMilli(plant.nextWateringAtEpoch)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        val today = LocalDate.now()
        val lastWateredDate = Instant.ofEpochMilli(plant.lastWateredAtEpoch)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        val isNextToday = nextDate.isEqual(today)
        val lastWateredToday = lastWateredDate.isEqual(today)

        // 규칙:
        // 1) 급수날이 오늘이 아니면 기본 회색.
        //    단, 오늘 물을 준 상태(wateringStatus=true && lastWateredToday=true)이면 파란 유지.
        // 2) 급수날이 오늘이면 status로 판단: false=경고, true=파랑.
        when {
            !isNextToday && lastWateredToday && plant.wateringStatus -> 1 // 오늘 급수했으므로 파랑 유지
            !isNextToday -> 2 // 급수날이 아님: 회색
            isNextToday && !plant.wateringStatus -> 0 // 오늘이고 미급수: 경고
            isNextToday && plant.wateringStatus -> 1 // 오늘이고 급수 완료: 파랑
            else -> 2
        }
    }

    val dDayText = remember(plant.nextWateringAtEpoch) {
        val nextDate = Instant.ofEpochMilli(plant.nextWateringAtEpoch)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        val today = LocalDate.now()
        val days = ChronoUnit.DAYS.between(today, nextDate).toInt()
        when {
            days == 0 -> "D-DAY"
            days > 0 -> "D-$days"
            else -> "D+${kotlin.math.abs(days)}"
        }
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
                // 다음 급수 계산
                // (마지막 급수일 + 주기) - (오늘 날짜)
                Text(
                    text = "다음 급수 $dDayText",
                    fontSize = 15.sp,
                    color = Color(0xFF0084FF)
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun WaterPlantCardPre() {
    PlantWateringTheme {
        WaterPlantCard(
            plants[2], onClick = {}
        )
    }
}