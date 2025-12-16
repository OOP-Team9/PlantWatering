package com.example.plantwatering.presentation.screen.watering

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantwatering.presentation.model.enums.WaterTab
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.data.remote.dto.toUi
import com.example.plantwatering.presentation.screen.watering.components.InfoBox
import com.example.plantwatering.presentation.screen.watering.components.TabButton
import com.example.plantwatering.presentation.screen.watering.components.WaterHistory
import com.example.plantwatering.presentation.screen.watering.components.WaterList
import com.example.plantwatering.presentation.viewmodel.WateringViewModel
import com.example.plantwatering.presentation.viewmodel.WateringViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.time.Duration

@Composable
fun WateringScreen(
){
    val vm: WateringViewModel = viewModel(factory = WateringViewModelFactory())
    val state by vm.uiState.collectAsState()

    LaunchedEffect(Unit) {
        vm.loadPlants()
    }

    var selectedTab by remember { mutableStateOf(WaterTab.WATER) }
    val selectedId = state.selectedPlantId

    LaunchedEffect(selectedTab, selectedId) {
        if (selectedTab == WaterTab.HISTORY) {
            selectedId?.let { vm.loadHistories(it) }
        }
    }
    // 기본은 물 주기 탭으로 설정
    Column {
        val uiPlants = state.plants.map { it.toUi() }
        InfoBox(
            count = uiPlants.size,
            plants = uiPlants
        )
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
            WaterTab.WATER -> WaterList(
                plants = uiPlants,
                selectedId = selectedId,
                onSelect = { id ->
                    vm.selectPlant(id)
                    vm.loadHistories(id) // 카드 선택 시 해당 식물 히스토리 즉시 로드
                },
                onWater = {
                    selectedId?.let { vm.waterPlant(it) }
                }
            )
            WaterTab.HISTORY -> {
                val nameMap = remember(state.plants) {
                    state.plants.associateBy({ it.plantId }, { it.name })
                }
                val historyUi = state.histories.map { h ->
                    val duration = Duration.between(h.wateredAt, java.time.Instant.now())
                    val text = when {
                        duration.toMinutes() < 1 -> "방금 전"
                        duration.toHours() < 1 -> "${duration.toMinutes()}분 전"
                        duration.toDays() < 1 -> "${duration.toHours()}시간 전"
                        duration.toDays() < 7 -> "${duration.toDays()}일 전"
                        else -> {
                            val formatter = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA)
                            formatter.format(Date.from(h.wateredAt))
                        }
                    }
                    com.example.plantwatering.presentation.screen.watering.components.HistoryUi(
                        plantName = nameMap[h.plantId] ?: "알 수 없음",
                        wateredAtText = text
                    )
                }
                WaterHistory(historyUi)
            }
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