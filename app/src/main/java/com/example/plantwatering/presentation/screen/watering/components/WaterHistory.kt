package com.example.plantwatering.presentation.screen.watering.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantwatering.presentation.screen.watering.components.HistoryUi
import com.example.plantwatering.presentation.screen.watering.components.plants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WaterHistory(histories: List<HistoryUi>){
    Column {
        LazyColumn {
            items(histories) { h ->
                WaterHistoryCard(h)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WaterHisPre(){
    val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA)
    val sample = listOf(
        HistoryUi("몬스테라", sdf.format(Date())),
        HistoryUi("로즈마리", sdf.format(Date()))
    )
    WaterHistory(sample)
}