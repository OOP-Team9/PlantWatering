package com.example.plantwatering.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantwatering.ui.theme.PlantWateringTheme
import com.example.plantwatering.R

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.Alarm

import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Grass
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.Alarm

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plantwatering.ui.theme.BackGroundGreen
import com.example.plantwatering.ui.theme.BoxGreen


// data 쪽으로 옮겨야 하나
data class BottomNavigationItem(
    val title : String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    // 급수 해야 할 식물 표시 기능 추가
    var badgeCount: Int? = null,
    val route: String
)

enum class Screens {
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
        route = Screens.HomeScreen.name
    ),
    BottomNavigationItem(
        title = "Tip",
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Outlined.List,
        route = Screens.TipScreen.name
    ),
    BottomNavigationItem(
        title = "Water",
        selectedIcon = Icons.Filled.WaterDrop,
        unselectedIcon = Icons.Outlined.WaterDrop,
        badgeCount = 3,
        route = Screens.WateringScreen.name
    ),
    BottomNavigationItem(
        title = "Alarm",
        selectedIcon = Icons.Filled.Alarm,
        unselectedIcon = Icons.Outlined.Alarm,
        route = Screens.AlarmScreen.name
    ),
)


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlantWateringTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun HomeScreen(){
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text(text = "HomeScreen",
            fontSize = 22.sp)
    }
}

@Composable
fun TipScreen(){
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text(text = "TipScreen",
            fontSize = 22.sp)
    }
}

@Composable
fun WateringScreen(){
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text(text = "WateringScreen",
            fontSize = 22.sp)
    }
}

@Composable
fun AlarmScreen(){
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text(text = "AlarmScreen",
            fontSize = 22.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
            bottomBar = { //NavigationBar 배치
                //위로 올려야함, 가로세로 조정 필요
                Box(
                    modifier = Modifier
                        .fillMaxWidth() ,
                    contentAlignment = Alignment.Center
                ){
                    NavigationBar(
                    modifier = Modifier
                        .padding(bottom = 61.dp)
                        .height(69.dp)
                        .width(297.dp)
                        .shadow(8.dp, RoundedCornerShape(40.dp))
                        .graphicsLayer {
                            shape = RoundedCornerShape(45.dp)
                            clip = true
                        },
                    containerColor = BoxGreen,
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        items.forEachIndexed { index, item ->
                        val selected = selectedItemIndex == index

                        NavigationBarItem(
                            selected = (selectedItemIndex == index),
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.route){
                                    popUpTo(0){
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                BadgedBox(
                                    modifier = Modifier
                                        //.padding(-40.dp)
                                        .background(
                                            color = if (selected) BoxGreen else Color.Transparent,
                                            shape = CircleShape
                                        ),
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            }
                        )
                    }
                    }}
                }
            }
        ){paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screens.HomeScreen.name,
                modifier = Modifier.padding(paddingValues)
            ){
                composable(route = Screens.HomeScreen.name){
                    HomeScreen()
                }
                composable(route = Screens.TipScreen.name){
                    TipScreen()
                }
                composable(route = Screens.WateringScreen.name){
                    WateringScreen()
                }
                composable(route = Screens.AlarmScreen.name){
                    AlarmScreen()
                }
            }
        }
    }
}

//-------//
@Composable
fun AttendEntry(id: String, name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(5.dp)
    ){
        Image(
            painter = painterResource(R.drawable.girl),
            contentDescription = "Girl icon",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(7.dp))
        Column{
            Text(
                text = id
            )
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlantWateringTheme {
        AttendEntry(id = "2024125052", name="성춘향")
    }
}