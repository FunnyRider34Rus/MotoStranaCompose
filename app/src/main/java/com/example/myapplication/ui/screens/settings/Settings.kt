package com.example.myapplication.ui.screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.database.AUTH
import com.example.myapplication.ui.navigation.AuthScreen
import com.example.myapplication.ui.navigation.SettingsScreen
import com.example.myapplication.ui.screens.BottomNavigationMenu
import com.example.myapplication.ui.theme.MyApplicationTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Settings(navController: NavController) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomNavigationMenu(navController = navController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_search),
                    contentDescription = stringResource(R.string.button_back_description),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { },
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_logout),
                    contentDescription = stringResource(R.string.button_back_description),
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            AUTH.signOut()
                            navController.navigate(AuthScreen.Welcome.route)
                        },
                )
            }
            Text(
                text = stringResource(id = R.string.settings),
                modifier = Modifier.padding(top = 40.dp),
                style = MaterialTheme.typography.h1
            )
            Text(
                text = "Профиль",
                modifier = Modifier
                    .padding(top = 40.dp)
                    .clickable { navController.navigate(SettingsScreen.Profile.route) },
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "Оповещения",
                modifier = Modifier.padding(top = 32.dp),
                style = MaterialTheme.typography.h2
            )
            Text(
                text = "Конфиденциальность",
                modifier = Modifier.padding(top = 32.dp),
                style = MaterialTheme.typography.h2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    MyApplicationTheme {
        Settings(navController = rememberNavController())
    }
}