package com.example.myapplication.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.database.AUTH
import com.example.myapplication.ui.navigation.BottomBarScreen
import com.example.myapplication.ui.navigation.SetupNavGraph
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.white
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun MainScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {

    }
    if (AUTH.currentUser?.uid == null) {
        navController.navigate(Screen.Welcome.route)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Dashboard,
        BottomBarScreen.Messages,
        BottomBarScreen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        modifier = Modifier.height(88.dp),
        backgroundColor = white,
        contentColor = black,
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = screen.icon),
                contentDescription = "navigation icon"
            )
        },
        /*label = {
            Text(text = screen.title, textAlign = TextAlign.Center)
        },*/
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
    )
}