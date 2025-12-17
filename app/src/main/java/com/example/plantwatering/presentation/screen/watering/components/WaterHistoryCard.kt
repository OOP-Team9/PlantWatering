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
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

data class HistoryUi(
    val plantName: String,
    val wateredAtText: String
)

@Composable
fun WaterHistoryCard(
    history: HistoryUi
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding( horizontal = 14.dp, vertical = 5.dp)
    ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.history_water),
                    contentDescription = "물방울",
                    modifier = Modifier
                        .size(44.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = history.plantName,
                        fontSize = 17.sp,
                    )

                    Text(
                        text = history.wateredAtText,
                        fontSize = 15.sp,
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



@Preview(showBackground = true)
@Composable
fun WaterHistoryPre() {
    val sample = HistoryUi("몬스테라", "2025")
    PlantWateringTheme {
        WaterHistoryCard(sample)
    }
}