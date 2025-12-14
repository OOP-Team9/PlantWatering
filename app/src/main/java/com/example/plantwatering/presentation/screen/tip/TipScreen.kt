package com.example.plantwatering.presentation.screen.tip

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantwatering.R
import com.example.plantwatering.presentation.model.ui.theme.BackGroundGreen
import com.example.plantwatering.presentation.model.ui.theme.BoxGreen
import com.example.plantwatering.presentation.model.ui.theme.testFamily
import com.example.plantwatering.presentation.screen.tip.components.PlantInfoCard
import com.example.plantwatering.presentation.screen.tip.components.QuestionInputBox
import com.example.plantwatering.presentation.screen.tip.components.SearchBar
import com.example.plantwatering.presentation.viewmodel.ChatViewModel


@Composable
fun SpeechBubbleWithImage(text: String, modifier: Modifier) {
    Box(
        modifier = Modifier
            .width(220.dp)
            .height(120.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.speech_bubble),
            contentDescription = "말풍선",
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = testFamily,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun AnswerBox(text: String){
    if(text.isBlank()) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = text,
            fontFamily = testFamily,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}
@Composable
fun TipScreen(
    chatViewModel: ChatViewModel = viewModel()
){
    val answer = chatViewModel.answer

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            SearchBar()

            Text(
                text = "식물 도감",
                fontSize = 23.sp,
                fontFamily = testFamily,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp, top = 10.dp)
            )

            PlantInfoCard(
                name = "몬스테라",
                englishName = "Monstera deliciosa",
                light = "간접광",
                water = "주 1-2회",
                humidity = "중간-높음",
                description = "큰 잎에 구멍이 생기는 특징적인 식물입니다. 과습에 주의하고 앞에 먼지가 쌓이지 않도록 관리하세요.",
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SpeechBubbleWithImage(
                    text = "내가 평소에 먹는 식물들!\n많이 알려줄게!\n그렇다고 먹진 말궁~",
                    modifier = Modifier
                        .align(Alignment.Top)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.dino),
                    contentDescription = "공룡",
                    modifier = Modifier.size(150.dp)
                )
            }

            AnswerBox(text = answer)

            QuestionInputBox(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                onSendClick = { input ->
                    if(input.isNotBlank()) {
                        chatViewModel.sendMessage(input)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TipScreenPreview() {
    TipScreen()
}