package com.example.plantwatering.presentation.screen.tip.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.R
import com.example.plantwatering.domain.model.Book
import com.example.plantwatering.presentation.model.ui.theme.BoxGreen
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.presentation.model.ui.theme.dropShadow
import com.example.plantwatering.presentation.model.ui.theme.testFamily

@Composable
fun PlantInfoCard(
    book: Book
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(15.dp),
                blur = 4.dp
            )
            .background(Color.White, RoundedCornerShape(15.dp))
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.plant),
                contentDescription = "식물 이미지",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Column {
                Text(text = book.plantName, fontSize = 20.sp)
                Text(text = book.plantEngName, fontSize = 16.sp, color = Color.Gray)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                InfoBox("☀️", "빛", book.lightInfo)
                InfoBox("💧", "물", book.waterInfo)
                InfoBox("🌫️", "습도", book.humidityInfo)
            }

            Text(text = book.description, fontSize = 16.sp)
        }
    }
}

@Composable
fun InfoBox(icon: String, title: String, value: String) {
    Box(
        modifier = Modifier
            .background(BoxGreen, RoundedCornerShape(12.dp))
            .padding(12.dp)
            .width(85.dp)
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(icon, fontSize = 16.sp)
            Text(title, fontSize = 16.sp)
            Text(value, fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantInfoCardPreview() {
    PlantWateringTheme {
        PlantInfoCard(
            book = Book(
                bookId = "1",
                plantName = "몬스테라",
                plantEngName = "Monstera deliciosa",
                imageUrl = "",
                lightInfo = "간접광",
                waterInfo = "주 1-2회",
                humidityInfo = "중간-높음",
                description = "큰 잎에 구멍이 생기는 특징적인 식물입니다. 과습에 주의하고 앞에 먼지가 쌓이지 않도록 관리하세요."
            )
        )
    }
}
