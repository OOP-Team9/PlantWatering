package com.example.plantwatering.presentation.components

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

//Navagation
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

//Screen
import com.example.plantwatering.presentation.screen.AlarmScreen
import com.example.plantwatering.presentation.screen.HomeScreen
import com.example.plantwatering.presentation.screen.TipScreen
import com.example.plantwatering.presentation.screen.WateringScreen

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

//Colors
import com.example.plantwatering.ui.theme.AlarmOffGray
import com.example.plantwatering.ui.theme.BackGroundGreen
import com.example.plantwatering.ui.theme.BoxGreen
import com.example.plantwatering.ui.theme.ButtonGreen
import com.example.plantwatering.ui.theme.White


//data class도 분리하는지 궁금
data class BottomNavigationItem(
    val title : String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    // 급수 해야 할 식물 표시 기능 추가
    var badgeCount: Int? = null,
    val route: String
)

enum class Routes { // enum 으로 루트 관리 방법 사용 중 -> name 프로퍼티 자동 제공
    HomeScreen,
    TipScreen,
    WateringScreen,
    AlarmScreen
}

val items = listOf(
    BottomNavigationItem(
        title = "Home",
        selectedIcon = Icons.Filled.Grass,
        unselectedIcon = Icons.Outlined.Grass,
        route = Routes.HomeScreen.name
    ),
    BottomNavigationItem(
        title = "Tip",
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List,
        route = Routes.TipScreen.name
    ),
    BottomNavigationItem(
        title = "Water",
        selectedIcon = Icons.Filled.WaterDrop,
        unselectedIcon = Icons.Outlined.WaterDrop,
        badgeCount = 3, //상태 기반으로 변경
        route = Routes.WateringScreen.name
    ),
    BottomNavigationItem(
        title = "Alarm",
        selectedIcon = Icons.Filled.Alarm,
        unselectedIcon = Icons.Outlined.Alarm,
        route = Routes.AlarmScreen.name
    ),
)

@Composable
fun AppNavigation(){
    val navController : NavHostController = rememberNavController()

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    var selected = true

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackGroundGreen
    ) {
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
                            .width(297.dp)
                            .dropShadow(RoundedCornerShape(45.dp))
                            .background(White, RoundedCornerShape(45.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                0.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
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
                                                popUpTo(0) { inclusive = true }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    BadgedBox(
                                        badge = {
                                            if (item.badgeCount != null) {
                                                Badge {
                                                    Text(text = item.badgeCount.toString())
                                                }
                                            }
                                        }
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
            }

        ){paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Routes.HomeScreen.name,
                modifier = Modifier.padding(paddingValues)
            ){
                composable(route = Routes.HomeScreen.name){
                    HomeScreen()
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
}

//그림자 확장 함수
@Composable
fun Modifier.dropShadow(
    shape: Shape,
    color: Color = Color.Black.copy(0.25f),
    blur: Dp = 7.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp
) = composed {
    val density = LocalDensity.current

    val paint = remember(color, blur) {
        Paint().apply {
            this.color = color
            val blurPx = with(density) { blur.toPx() }
            if (blurPx > 0f) {
                this.asFrameworkPaint().maskFilter =
                    BlurMaskFilter(blurPx, BlurMaskFilter.Blur.NORMAL)
            }
        }
    }

    drawBehind {
        val spreadPx = spread.toPx()
        val offsetXPx = offsetX.toPx()
        val offsetYPx = offsetY.toPx()

        val shadowWidth = size.width + spreadPx
        val shadowHeight = size.height + spreadPx

        if (shadowWidth <= 0f || shadowHeight <= 0f) return@drawBehind

        val shadowSize = Size(shadowWidth, shadowHeight)
        val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)

        drawIntoCanvas { canvas ->
            canvas.save()
            canvas.translate(offsetXPx, offsetYPx)
            canvas.drawOutline(shadowOutline, paint)
            canvas.restore()
        }
    }
}