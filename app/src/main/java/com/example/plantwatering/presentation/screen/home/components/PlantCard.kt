package com.example.plantwatering.presentation.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.R
import com.example.plantwatering.presentation.model.ui.theme.StatusRed
import com.example.plantwatering.presentation.model.ui.theme.logo
import com.example.plantwatering.presentation.model.ui.theme.testFamily
import com.example.plantwatering.presentation.screen.home.HomeScreen


@Composable
fun PlantCard(
    name: String,
    period: String,
    lastWatering: String,
    onWriteClick: () -> Unit
){
    Box(
        modifier = Modifier
            .width(383.dp)
            .wrapContentHeight()
            .background(Color.White, RoundedCornerShape(15.dp))
            .padding(top = 15.dp, end = 15.dp, bottom = 15.dp)
    ){
        Row(
            modifier = Modifier.wrapContentSize()
        ){
            Image(
                painter= painterResource(id = R.drawable.plant),
                contentDescription = null,
                modifier = Modifier
                    .size(125.dp)
            )
            Column (
                modifier = Modifier.fillMaxWidth()
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = testFamily,
                        modifier = Modifier.padding(end = 85.dp)
                    )

                    Box(
                        modifier = Modifier
                            .background(StatusRed, RoundedCornerShape(20.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ){
                        Text(
                            text = "물 필요",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = testFamily
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .background(Color.White)
                ) {
                    Text(
                        text = "급수 주기: $period",
                        fontSize = 15.sp,
                        fontFamily = testFamily,
                        color = Color.Black
                    )
                    Text(
                        text = "마지막 급수일 : $lastWatering",
                        fontSize = 15.sp,
                        fontFamily = testFamily,
                        color = Color.Black
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .background(Color(0xFF5AB75E), RoundedCornerShape(25.dp))
                        .fillMaxWidth()
                        .height(40.dp)
                        .clickable { onWriteClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "일지 적기",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = testFamily
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantCardPre() {
    PlantCard(
        name = "몬스테라",
        period = "5일",
        lastWatering = "2025.11.04",
        onWriteClick = { /* 일지 적기 버튼 눌렀을 때 */ }
    )
}