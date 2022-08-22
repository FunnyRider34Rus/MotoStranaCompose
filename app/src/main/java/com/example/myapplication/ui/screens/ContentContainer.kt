package com.example.myapplication.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.database.AUTH
import com.example.myapplication.ui.navigation.BottomBarScreen
import com.example.myapplication.ui.navigation.Graph
import com.example.myapplication.ui.navigation.MainNavGraph
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ContentContainer(navController: NavHostController = rememberNavController()) {

    CheckAuthUser(navController)

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        MainNavGraph(navController = navController)
    }
}

@Composable
fun CheckAuthUser(navController: NavHostController) {
    LaunchedEffect(key1 = Unit) {
        if (AUTH.currentUser?.uid == null) {
            navController.navigate(Graph.AUTHENTICATION)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Dashboard,
        BottomBarScreen.Profile,
        BottomBarScreen.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation (
            modifier = Modifier.height(88.dp),
            backgroundColor = white,
            contentColor = black
                ){
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
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
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = screen.icon),
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MyApplicationTheme {
        ContentContainer(navController = rememberNavController())
    }
}