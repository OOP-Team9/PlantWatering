package com.example.plantwatering.presentation.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.R
import com.example.plantwatering.domain.model.Plant
import com.example.plantwatering.presentation.model.ui.theme.ButtonGreen
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.presentation.model.ui.theme.StatusRed
import com.example.plantwatering.presentation.model.ui.theme.dropShadow
import java.time.Instant


@Composable
fun PlantCard(
    plant: Plant,
    lastWatering: String,
    onWriteClick: (String) -> Unit
){
    Box(
        modifier = Modifier
            .width(383.dp)
            .wrapContentHeight() //내용물 높이만큼
            .dropShadow( //ShadowExtensions에 있음
                shape = RoundedCornerShape(15.dp),
                blur = 4.dp
            )
            .background(Color.White, RoundedCornerShape(15.dp)) //shadow 때문에 박스 색 있어야 함
            .padding(15.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.plant),
                contentDescription = "식물 사진",
                modifier = Modifier.size(110.dp)
            )

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
            ){
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = plant.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 70.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = "급수 주기: ${plant.wateringIntervalDays} 일",
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "마지막 급수일 : $lastWatering",
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .background(ButtonGreen, RoundedCornerShape(25.dp))
                        .fillMaxWidth()
                        .height(40.dp)
                        .clickable { onWriteClick(plant.plantId) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "일지 적기",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantCardPreview() {
    PlantWateringTheme {
        PlantCard(
            plant = Plant(
                plantId = "1",
                name = "몬스테라",
                wateringIntervalDays = 5,
                lastWateredAt = Instant.now(),
                nextWateringAt = Instant.now(),
                photoUrl = null,
                species = "Monstera deliciosa",
                wateringStatus = false
            ),
            lastWatering = "2025.11.04",
            onWriteClick = {}
        )
    }
}