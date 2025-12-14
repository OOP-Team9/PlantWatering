package com.example.plantwatering.presentation.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.presentation.viewmodel.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = DetailViewModel()
) {
    val name by viewModel.plantName.collectAsState()
    val dDay by viewModel.dDay.collectAsState()

    val interval by viewModel.wateringInterval.collectAsState()
    val lastDate by viewModel.lastWaterDate.collectAsState()
    val species by viewModel.species.collectAsState()
    val diary by viewModel.diary.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        // 제목
        Text(text = "상세페이지", fontSize = 22.sp)

        Spacer(Modifier.height(20.dp))

        // 상단 카드 (사진 + 이름 + D-DAY 테두리 안에 함께)
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 왼쪽: 사진 자리
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("사진")
                    // 나중에 여기 AsyncImage로 교체
                }

                Spacer(Modifier.width(16.dp))

                // 오른쪽: 이름 + D-DAY
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = name, fontSize = 24.sp)
                    Spacer(Modifier.height(8.dp))
                    Text(text = "다음 급수 $dDay", fontSize = 16.sp, color = Color.Gray)
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // 물주기 간격(일)
        Text(text = "물주기 간격(일)", fontSize = 14.sp)
        OutlinedTextField(
            value = interval,
            onValueChange = viewModel::updateInterval,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        // 마지막 급수일
        Text(text = "마지막 급수일", fontSize = 14.sp)
        OutlinedTextField(
            value = lastDate,
            onValueChange = viewModel::updateLastDate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        // 식물 종
        Text(text = "식물 종", fontSize = 14.sp)
        OutlinedTextField(
            value = species,
            onValueChange = viewModel::updateSpecies,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            singleLine = true
        )

        Spacer(Modifier.height(24.dp))

        // 일지 적기 제목
        Text(text = "일지 적기", fontSize = 16.sp)

        Spacer(Modifier.height(8.dp))

        // 일지 적기 입력 박스
        OutlinedTextField(
            value = diary,
            onValueChange = viewModel::updateDiary,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFBDBDBD),
                unfocusedBorderColor = Color(0xFFBDBDBD)
            )
        )

        Spacer(Modifier.height(24.dp))

        // 하단 버튼
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { /* TODO: 취소 동작 */ },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text("취소")
            }

            Spacer(Modifier.width(12.dp))

            Button(
                onClick = { /* TODO: 저장 동작 */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5AB75E)
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text("저장", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPre() {
    DetailScreen()
}
