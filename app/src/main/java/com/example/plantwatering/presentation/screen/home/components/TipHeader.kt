package com.example.plantwatering.presentation.screen.home.components

import androidx.appcompat.widget.DialogTitle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import com.example.plantwatering.presentation.model.ui.theme.BoxGreen
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.presentation.model.ui.theme.testFamily

@Composable
fun PlantTipBox(
    title: String,
    content: String
){
    Box(
        modifier = Modifier
            .width(383.dp)
            .wrapContentHeight() //내용물 변경될 때마다 높이 다시 조정(동적 크기 변경)
            .background(
                color = BoxGreen,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(top = 8.dp, bottom = 8.dp, start = 10.dp, end = 8.dp)
    ){
        Column(modifier = Modifier.wrapContentHeight()) {
            Text(
                text = "\uD83D\uDCA1 $title",
                color = Color.Black,
                fontFamily = testFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier= Modifier
                    .padding(top = 15.dp, bottom = 8.dp)
            )

            Text(
                text = "🪴 $content",
                color = Color.Black,
                fontSize = 15.sp,
                fontFamily = testFamily,
                modifier = Modifier
                    .padding(bottom = 15.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantTipBoxPreview(){
    PlantWateringTheme {
        PlantTipBox(
            "오늘의 식물 팁",
            "화분 바닥에 배수 구멍이 있어야 뿌리가 썩지 않아요."
        )
    }
}