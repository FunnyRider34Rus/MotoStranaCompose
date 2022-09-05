package com.example.myapplication.ui.screens.messages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.BottomNavigationMenu
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.white
import com.example.myapplication.common.AnimatedIndicator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Messages(navController: NavController){

    var tabIndex by rememberSaveable { mutableStateOf(0) }
    val titles = listOf("Область", "Город")
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        AnimatedIndicator(
            tabPositions = tabPositions,
            selectedTabIndex = tabIndex
        )
    }

    Scaffold(
        bottomBar = { BottomNavigationMenu(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            //Табсы с новостями и мероприятиями
            TabRow(
                selectedTabIndex = tabIndex,
                backgroundColor = white,
                contentColor = black,
                indicator = indicator
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = {
                            tabIndex = index
                        },
                        text = {
                            Text(
                                text = title,
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessagesPreview() {
    MyApplicationTheme {
        Messages(navController = rememberNavController())
    }
}