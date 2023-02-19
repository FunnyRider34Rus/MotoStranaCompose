package com.example.motostranacompose.ui

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.motostranacompose.ui.navigation.BottomBarScreen
import com.example.motostranacompose.ui.navigation.MainNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = { },
        bottomBar = { BottomBar(navController = navController) },
        snackbarHost = { }
    ) {
        MainNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomBarScreen.dashboard_list,
        BottomBarScreen.messages,
        BottomBarScreen.rideMode,
        BottomBarScreen.profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        val bottomBarDestination = screens.any { it.route == currentDestination?.route }
        if (bottomBarDestination) {     //если текущий экран не в bottomBar, то скрываем его
            screens.forEach { item ->
                NavigationBarItem(
                    label = { Text(item.title) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null
                        )
                    },
                    alwaysShowLabel = false,
                    selected = currentDestination?.route == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}
