package com.example.plantwatering.presentation.screen.watering

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.presentation.model.enums.WaterTab
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.presentation.screen.watering.components.InfoBox
import com.example.plantwatering.presentation.screen.watering.components.Plant
import com.example.plantwatering.presentation.screen.watering.components.TabButton
import com.example.plantwatering.presentation.screen.watering.components.WaterHistory
import com.example.plantwatering.presentation.screen.watering.components.WaterList
import com.example.plantwatering.presentation.screen.watering.components.plants

@Composable
fun WateringScreen(
){
    var selectedTab by remember { mutableStateOf(WaterTab.WATER) }
    // 기본은 물 주기 탭으로 설정
    Column {
        InfoBox(3,plants) // 여기에 물 줘야 하는 식물 데이터
        // 탭 버튼
        Box(){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                TabButton("물 주기", selectedTab == WaterTab.WATER) {
                    selectedTab = WaterTab.WATER
                }
                TabButton("히스토리", selectedTab == WaterTab.HISTORY) {
                    selectedTab = WaterTab.HISTORY
                }
            }
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            )
        }


        Spacer(modifier = Modifier.height(20.dp))

        // 탭에 따라 내용 전환
        when (selectedTab) {
            WaterTab.WATER -> WaterList(plants)
            WaterTab.HISTORY -> WaterHistory()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WateringScreenPre() {
    PlantWateringTheme {
        WateringScreen()
    }
}