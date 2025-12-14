package com.example.plantwatering.presentation.screen.tip.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.presentation.model.ui.theme.BoxGreen
import com.example.plantwatering.presentation.model.ui.theme.testFamily

@Composable
fun QuestionInputBox(
    modifier: Modifier = Modifier,
    onSendClick: (String) -> Unit = {}
) {
    var question by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(BoxGreen, RoundedCornerShape(20.dp))
            .padding(horizontal = 5.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = question,
            onValueChange = { question = it },
            modifier = Modifier
                .height(60.dp),
            placeholder = {
                Text(
                    text = "여기에 질문을 작성해 주세요...",
                    fontSize = 14.sp,
                    fontFamily = testFamily,
                    color = Color.DarkGray
                )
            },
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { onSendClick(question) },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5AB75E),
                contentColor = Color.White
            )
        ) {
            Text("전송", fontFamily = testFamily)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionInputBoxPreview() {
    QuestionInputBox()
}