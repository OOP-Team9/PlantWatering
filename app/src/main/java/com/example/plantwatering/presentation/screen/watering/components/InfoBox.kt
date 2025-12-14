package com.example.plantwatering.presentation.screen.watering.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.presentation.model.ui.theme.testFamily
import kotlin.Int
import kotlin.String
import com.example.plantwatering.presentation.screen.watering.components.*


// 깡통 용
// 물 줘야 할 식물 수와 식물을 쿼리 조회할 때  필터링해서 얘한테 전달해주는..!

@Composable
fun InfoBox(
   count: Int,
   plants: List<Plant>
){
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .padding(start = 20.dp, top = 110.dp, bottom = 40.dp)
    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            Text(
                text = "\uD83D\uDCA7 오늘 물 줄 식물: ${count}개",
                fontSize = 20.sp,
                fontFamily = testFamily
            )
            Row(){
                Text(
                    text = "\uD83C\uDF31 ",
                    fontSize = 20.sp,
                    fontFamily = testFamily
                )
                var list:String = ""
                for( plant in plants){
                    list += plant.name
                    list += ", "
                }
                //val list = plants.joinToString(", ") { it.name }
                Text(
                    text = "${list}",
                    fontSize = 20.sp,
                    fontFamily = testFamily
                )

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun InfoBoxPre() {
    InfoBox(
        3,
        plants
    )
}