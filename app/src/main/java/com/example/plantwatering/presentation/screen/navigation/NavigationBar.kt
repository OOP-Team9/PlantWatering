package com.example.plantwatering.presentation.screen.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//Navagation
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

//Screen
import com.example.plantwatering.presentation.screen.alarm.AlarmScreen
import com.example.plantwatering.presentation.screen.home.HomeRoute
import com.example.plantwatering.presentation.screen.tip.TipScreen
import com.example.plantwatering.presentation.screen.watering.WateringScreen

//Icons
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material.icons.outlined.Grass
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.ui.draw.dropShadow
import com.example.plantwatering.presentation.model.NavigationItem
import com.example.plantwatering.presentation.model.enums.Routes

//Colors
import com.example.plantwatering.presentation.model.ui.theme.AlarmOffGray
import com.example.plantwatering.presentation.model.ui.theme.BoxGreen
import com.example.plantwatering.presentation.model.ui.theme.ButtonGreen
import com.example.plantwatering.presentation.model.ui.theme.White
import com.example.plantwatering.presentation.model.ui.theme.dropShadow

@Composable
fun AppNavigation(){
    val navController : NavHostController = rememberNavController()

    val items = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Grass,
            unselectedIcon = Icons.Outlined.Grass,
            route = Routes.HomeScreen.name
        ),
        NavigationItem(
            title = "Tip",
            selectedIcon = Icons.Filled.List,
            unselectedIcon = Icons.Outlined.List,
            route = Routes.TipScreen.name
        ),
        NavigationItem(
            title = "Water",
            selectedIcon = Icons.Filled.WaterDrop,
            unselectedIcon = Icons.Outlined.WaterDrop,
            route = Routes.WateringScreen.name
        ),
        NavigationItem(
            title = "Alarm",
            selectedIcon = Icons.Filled.Alarm,
            unselectedIcon = Icons.Outlined.Alarm,
            route = Routes.AlarmScreen.name
        )
    )

    var selectedItemIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 61.dp)
                            .height(69.dp)
                            .dropShadow(
                                shape = RoundedCornerShape(45.dp),
                                transparency = 0.15f ,
                                blur = 7.dp)
                            .background(
                                color=White,
                                shape=RoundedCornerShape(45.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(5.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            items.forEachIndexed { index, item ->

                                val isSelected = selectedItemIndex == index

                                Box(
                                    modifier = Modifier
                                        .width(71.dp)
                                        .height(58.dp)
                                        .background(
                                            color = if (isSelected) BoxGreen else Color.Transparent,
                                            shape = RoundedCornerShape(45.dp)
                                        )
                                        .clickable {
                                            selectedItemIndex = index
                                            navController.navigate(item.route) {
                                                // 네비게이션 스택 특정 목적지까지 pop 후 이동
                                                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                                                // 같은 화면 다시 navigate할 때 새로 x, 재사용
                                                launchSingleTop = true
                                                // 이전에 해당 route가 pop 됏었다면 저장된 상태 복원
                                                restoreState = true
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                            modifier = Modifier.size(26.dp),
                                            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = item.title,
                                            tint = if (isSelected) ButtonGreen else AlarmOffGray
                                        )
                                }
                            }
                        }
                    }
                }
            }

        ){
        // 내부 콘텐츠에 자동으로 padding?
        paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Routes.HomeScreen.name,
                modifier = Modifier.padding(paddingValues)
            ){
                composable(route = Routes.HomeScreen.name){
                    HomeRoute()
                }
                composable(route = Routes.TipScreen.name){
                    TipScreen()
                }
                composable(route = Routes.WateringScreen.name){
                    WateringScreen()
                }
                composable(route = Routes.AlarmScreen.name){
                    AlarmScreen()
                }
            }
        }
    }
