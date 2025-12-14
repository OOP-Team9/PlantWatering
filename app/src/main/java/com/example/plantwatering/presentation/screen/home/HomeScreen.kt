package com.example.plantwatering.presentation.screen.home

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plantwatering.R
import com.example.plantwatering.presentation.model.enums.WaterTab
import com.example.plantwatering.presentation.model.ui.theme.BackGroundGreen
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.presentation.screen.home.components.PlantCard
import com.example.plantwatering.presentation.screen.home.components.PlantTipBox
import com.example.plantwatering.presentation.screen.register.RegisterScreen

private sealed class HomeRoute(val route: String) {
    object Register : HomeRoute("register")
    object Main : HomeRoute("home")
}

@Composable
fun PlusIcon(onClick: () -> Unit) {
    LargeFloatingActionButton(
        onClick = { onClick() },
        shape = CircleShape,
        containerColor = Color(0xFFCFD0CC),
        contentColor = Color.White,
        elevation = FloatingActionButtonDefaults.elevation(0.dp),
        modifier = Modifier.size(70.dp)
    ) {
        Icon(
            Icons.Filled.Add,
            null,
            modifier = Modifier.size(40.dp),
            tint = Color.White
            )
    }
}

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeMainScreen(
                onPlusClick = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen()
        }
    }
}
@Composable
private fun HomeMainScreen(
    onPlusClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundGreen)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            PlantTipBox(
                title = "오늘의 식물 팁",
                content = "화분 바닥에 배수 구멍이 있어야 뿌리가 썩지 않아요."
            )

            PlantCard(
                name = "몬스테라",
                period = "5일",
                lastWatering = "2025.11.04",
                onWriteClick = { }
            )

            PlantCard(
                name = "로즈마리",
                period = "5일",
                lastWatering = "2025.11.03",
                onWriteClick = { }
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            PlusIcon (onClick = onPlusClick)
        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PlantWateringTheme {
        HomeScreen()
    }
}
