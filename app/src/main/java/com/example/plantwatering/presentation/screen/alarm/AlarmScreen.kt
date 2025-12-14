package com.example.plantwatering.presentation.screen.alarm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.presentation.screen.alarm.components.AlarmInfoBox
import com.example.plantwatering.presentation.screen.watering.WateringScreen

@Composable
fun AlarmScreen(){
    AlarmInfoBox()
}

@Preview(showBackground = true)
@Composable
fun AlarmScreenPre() {
    PlantWateringTheme {
        AlarmScreen()
    }
}