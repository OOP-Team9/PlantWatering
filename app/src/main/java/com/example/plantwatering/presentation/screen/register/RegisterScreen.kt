package com.example.plantwatering.presentation.screen.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.presentation.screen.alarm.AlarmScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(viewModel: RegisterViewModel = RegisterViewModel()) {
    val name by viewModel.plantName.collectAsState()
    val interval by viewModel.wateringInterval.collectAsState()
    val lastDate by viewModel.lastWaterDate.collectAsState()
    val species by viewModel.species.collectAsState()
    val imageUrl by viewModel.imageUrl.collectAsState()

    var speciesExpanded by remember { mutableStateOf(false) }
    val speciesList = listOf("선택 안함", "로즈마리", "몬스테라", "스투키")

    Column(modifier = Modifier.padding(20.dp)) {
        Text("새로 추가할 식물", fontSize = 22.sp)

        Spacer(Modifier.height(20.dp))

        // 식물 이름
        InputWithCheck(
            label = "식물 이름",
            value = name,
            onValueChange = viewModel::updateName,
            isValid = name.isNotBlank()
        )

        Spacer(Modifier.height(12.dp))

        // 물 주기 간격
        InputWithCheck(
            label = "물 주기 간격(일)",
            value = interval,
            onValueChange = viewModel::updateInterval,
            isValid = interval.toIntOrNull()?.let { it > 0 } == true
        )

        Spacer(Modifier.height(12.dp))

        // 마지막 급수일
        InputWithCheck(
            label = "마지막 급수일 (YYYY.MM.DD)",
            value = lastDate,
            onValueChange = viewModel::updateLastDate,
            isValid = Regex("""\d{4}\.\d{2}\.\d{2}""").matches(lastDate)
        )

        Spacer(Modifier.height(20.dp))
        Text("선택 사항", fontSize = 18.sp)

        Spacer(Modifier.height(12.dp))

        // 식물 종
        ExposedDropdownMenuBox(
            expanded = speciesExpanded,
            onExpandedChange = { speciesExpanded = !speciesExpanded }
        ) {
            OutlinedTextField(
                value = species,
                onValueChange = {},
                readOnly = true,
                label = { Text("식물 종") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = speciesExpanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = speciesExpanded,
                onDismissRequest = { speciesExpanded = false }
            ) {
                speciesList.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            viewModel.updateSpecies(item)
                            speciesExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // 사진 URL
        OutlinedTextField(
            value = imageUrl,
            onValueChange = viewModel::updateImageUrl,
            label = { Text("식물 사진 URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(30.dp))

        // 버튼
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { /* TODO: 취소 */ },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text("취소")
            }

            Button(
                onClick = { /* TODO: 등록 */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5AB75E)),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text("등록", color = Color.White)
            }
        }
    }
}

@Composable
fun InputWithCheck(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier.weight(1f)
        )
        if (isValid) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Valid",
                tint = Color(0xFF5AB75E),
                modifier = Modifier
                    .padding(start = 8.dp, top = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPre() {
    PlantWateringTheme {
        RegisterScreen()
    }
}

