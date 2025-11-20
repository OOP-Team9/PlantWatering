package com.example.plantwatering.presentation.screen.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = RegisterViewModel()
) {
    val name = viewModel.plantName.collectAsState()
    val interval = viewModel.wateringInterval.collectAsState()
    val lastDate = viewModel.lastWaterDate.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text("새로 추가할 식물", fontSize = 22.sp)

        OutlinedTextField(
            value = name.value,
            onValueChange = { viewModel.updateName(it) },
            label = { Text("식물 이름") }
        )

        OutlinedTextField(
            value = interval.value,
            onValueChange = { viewModel.updateInterval(it) },
            label = { Text("물 주기 간격") }
        )

        OutlinedTextField(
            value = lastDate.value,
            onValueChange = { viewModel.updateLastDate(it) },
            label = { Text("마지막 급수일") }
        )
    }
}
