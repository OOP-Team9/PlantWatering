package com.example.plantwatering.presentation.screen.watering.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun WaterList(){
    Column(){
        WaterPlantCard(
            plants[0], onClick = {}
        )
        WaterPlantCard(
            plants[1], onClick = {}
        )
        WaterPlantCard(
            plants[2], onClick = {}
        )
    }

}