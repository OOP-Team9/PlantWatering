package com.example.plantwatering.presentation.screen.watering.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.presentation.model.ui.theme.ButtonGreen
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme

import androidx.compose.ui.platform.LocalContext
import com.example.plantwatering.presentation.screen.watering.components.PlantUi

@Composable
fun WaterList(
    plants: List<PlantUi>,
    selectedId: String?,
    onSelect: (String) -> Unit,
    onWater: () -> Unit
){
    Column(){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 375.dp)
        ) {
            items(plants){ plant ->
                WaterPlantCard(
                    plant = plant,
                    isSelected = plant.plantId == selectedId,
                    onClick = { onSelect(plant.plantId) }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 14.dp, end = 14.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .background(
                        color = ButtonGreen,
                        shape = RoundedCornerShape(25.dp)
                    )
                    .width(70.dp)
                    .height(40.dp)
                    .clickable { onWater() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "저장",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun WaterListPre() {
    PlantWateringTheme {
        WaterList(
            plants = plants,
            selectedId = plants.firstOrNull()?.plantId,
            onSelect = {},
            onWater = {}
        )
    }
}