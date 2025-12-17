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
import java.time.LocalDate

@Composable
fun WateringScreen(
){
    val vm: WateringViewModel = viewModel(factory = WateringViewModelFactory())
    // 상태 바뀌면 화면 자동 갱신
    val state = vm.uiState.collectAsState().value

    // remember로 탭 유지 + by 로 상자가 아닌 바로 값을 쓸 수 있게
    var selectedTab by remember { mutableStateOf(WaterTab.WATER) }
    // selectedId 는 상태로 받아서 사용 -> 로직과 직접적인 연관이 높아서
    val selectedId = state.selectedPlantId

    // UI 용 으로 만들어서 새 리스트로
    val uiPlants = state.plants.map { it.toUi() }

    // next가 오늘 이하 or last가 오늘인 애들
    val todayUiPlants = uiPlants
        .filter { it ->
            (it.nextWateringAt <= LocalDate.now()) || ( it.lastWateredAt == LocalDate.now())
        }

    Column {
        InfoBox(
            plants = todayUiPlants
        )
        // 탭 버튼
        Box(){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                TabButton(
                    text = "물 주기",
                    selected = (selectedTab == WaterTab.WATER)
                ) {
                    selectedTab = WaterTab.WATER
                }
                TabButton(
                    text = "히스토리",
                    selected = (selectedTab == WaterTab.HISTORY)
                ) {
                    selectedTab = WaterTab.HISTORY
                    selectedId?.let {
                        vm.loadHistories(it)
                    }
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
                val histories = state.histories.map { h ->
                    val plantName = state.plants.firstOrNull { it.plantId == h.plantId }?.name ?: "알 수 없음"
                    HistoryUi(
                        plantName = plantName,
                        wateredAtText = h.wateredAt.toString().take(10)
                    )
                }
                WaterHistory(histories)
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