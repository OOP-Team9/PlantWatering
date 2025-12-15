package com.example.plantwatering.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plantwatering.presentation.model.ui.theme.BackGroundGreen
import com.example.plantwatering.presentation.model.ui.theme.PlantWateringTheme
import com.example.plantwatering.presentation.screen.home.components.PlantCard
import com.example.plantwatering.presentation.screen.home.components.PlantTipBox
import com.example.plantwatering.presentation.screen.register.RegisterScreen
import com.example.plantwatering.presentation.model.enums.HomeTab
import com.example.plantwatering.presentation.model.ui.theme.Plusicon
import com.example.plantwatering.presentation.screen.register.DetailScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.plantwatering.presentation.viewmodel.TipViewModel
import com.example.plantwatering.presentation.viewmodel.TipViewModelFactory

@Composable
fun PlusIcon(onClick: () -> Unit) { //입력 안 받고 반환값 없고
    LargeFloatingActionButton(
        onClick = { onClick() }, //람다함수
        shape = CircleShape,
        containerColor = Plusicon,
        contentColor = Color.White,
        elevation = FloatingActionButtonDefaults.elevation(0.dp), //그림자 없음(FAB 기본값 없애기)
        modifier = Modifier.size(70.dp)
    ) {
        Icon(
            Icons.Filled.Add,
            "RegisterScreen으로 이동",
            modifier = Modifier.size(40.dp),
        )
    }
}

@Composable
fun HomeRoute() {
    val navController = rememberNavController() //리컴포지션이 일어나도 같은 컨트롤러 계속 유지

    NavHost(
        navController = navController,
        startDestination = HomeTab.HOME.route
    ) {
        composable(HomeTab.HOME.route) { //route가 HOME일 때 보여줄 화면
            HomeScreen(
                onPlusClick = {
                    navController.navigate(HomeTab.REGISTER.route)
                },
                onWriteClick = {
                    navController.navigate(HomeTab.DETAIL.route)
                }
            )
        }

        composable(HomeTab.REGISTER.route) {
            RegisterScreen()
        }

        composable(HomeTab.DETAIL.route) {
            DetailScreen()
        }
    }
}
@Composable
fun HomeScreen(
    onPlusClick: () -> Unit,
    onWriteClick: () -> Unit,
    tipViewModel: TipViewModel = viewModel(factory = TipViewModelFactory())
){
    val tipState by tipViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        tipViewModel.loadTip()
    }
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
                content = when {
                    tipState.isLoading -> "불러오는 중..."
                    tipState.error != null -> tipState.error ?: "팁을 불러오지 못했어요"
                    tipState.tip.isNullOrBlank() -> "팁이 아직 준비되지 않았어요."
                    else -> tipState.tip.orEmpty()
                }
            )

            PlantCard(
                name = "몬스테라",
                period = "5일",
                lastWatering = "2025.11.04",
                onWriteClick = onWriteClick
            )

            PlantCard(
                name = "로즈마리",
                period = "5일",
                lastWatering = "2025.11.03",
                onWriteClick = onWriteClick
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            PlusIcon (onClick = onPlusClick) //
        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PlantWateringTheme {
        HomeRoute()
    }
}
