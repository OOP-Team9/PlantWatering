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
import com.example.plantwatering.presentation.screen.watering.components.HistoryUi
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
import java.time.Instant

@Composable
fun WateringScreen(
){
    val vm: WateringViewModel = viewModel(factory = WateringViewModelFactory())
    // 상태 바뀌면 화면 자동 갱신
    val state by vm.uiState.collectAsState()

    var selectedTab by remember { mutableStateOf(WaterTab.WATER) }
    val selectedId = state.selectedPlantId

    //UI 용 plantUi로 ..
    val uiPlants = state.plants.map { it.toUi() }

    val dateFmt = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    val today = dateFmt.format(Date())

    val dueUiPlants = uiPlants
        .filter { plant ->
            val next = dateFmt.format(Date(plant.nextWateringAtEpoch))
            val last = dateFmt.format(Date(plant.lastWateredAtEpoch))

            // A 로직: next가 오늘 이하 OR last가 오늘
            (next <= today) || (last == today)
        }
        .sortedBy { it.nextWateringAtEpoch }


    Column {
        InfoBox(
            count = dueUiPlants.size,
            plants = dueUiPlants
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
                    selectedId?.let { vm.loadHistories(it) }
                }
            }
            //회색 줄
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
                    vm.loadHistories(id)
                },
                onWater = {
                    selectedId?.let { vm.waterPlant(it) }
                }
            )
            WaterTab.HISTORY -> {
                // 히스토리와 함께 식물 이름을 띄우기 위함
                val nameMap = mutableMapOf<String, String>()
                for (p in state.plants) {
                    nameMap[p.plantId] = p.name
                }

                val historyUi = state.histories.map { h ->
                    val duration = Duration.between(h.wateredAt, Instant.now())
                    val text = when {
                        duration.toMinutes() < 1 -> "방금 전"
                        duration.toHours() < 1 -> "${duration.toMinutes()}분 전"
                        duration.toDays() < 1 -> "${duration.toHours()}시간 전"
                        duration.toDays() < 7 -> "${duration.toDays()}일 전"
                        else -> {
                            dateFmt.format(Date.from(h.wateredAt))
                        }
                    }
                    HistoryUi(
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