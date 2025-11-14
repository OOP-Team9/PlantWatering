package com.example.plantwatering.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.Alarm

import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.vector.ImageVector


// data 쪽으로 옮겨야 하나
data class BottomNavigationItem(
    val title : String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    // 급수 해야 할 식물 표시 기능 추가
    var badgeCount: Int? = null
)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { //컴포저블 함수 : UI를 선언하기 위해 사용하는 함수
            PlantWateringTheme {
                val items = listOf(
                    BottomNavigationItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                    ),
                    BottomNavigationItem(
                        title = "Tip",
                        selectedIcon = Icons.Filled.Email,
                        unselectedIcon = Icons.Outlined.Email,
                    ),
                    BottomNavigationItem(
                        title = "Water",
                        selectedIcon = Icons.Filled.WaterDrop,
                        unselectedIcon = Icons.Outlined.WaterDrop,
                        badgeCount = 3,
                    ),
                    BottomNavigationItem(
                        title = "Alarm",
                        selectedIcon = Icons.Filled.Alarm,
                        unselectedIcon = Icons.Outlined.Alarm,
                    ),
                )
                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        //modifier = Modifier.fillMaxSize(),
                        bottomBar = { //NavigationBar 배치
                            NavigationBar {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
                                            //navController.navigate(item.title)
                                        },
                                        label = {
                                            Text(text = item.title)
                                        },
                                        //alwaysShowLabel = false,
                                        icon = {
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
                                                    imageVector = if (index == selectedItemIndex) {
                                                        item.selectedIcon
                                                    } else item.unselectedIcon,
                                                    contentDescription = item.title
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    ) {

                    }
                }
            }
        }
    }
}

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