package com.example.myapplication.ui.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.screens.Navigate
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.golbat_60

@Composable
fun Welcome(navController: NavController) {
    Column {
        Image(
            painter = painterResource(R.drawable.welcome_wallpaper),
            contentDescription = stringResource(R.string.welcome_image_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(340.dp)
                .fillMaxWidth(),
        )
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.padding(24.dp, 24.dp, 24.dp, 0.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h1
        )
        Text(
            text = stringResource(R.string.welcome_title_text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 8.dp, 24.dp, 0.dp),
            color = golbat_60,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h3
        )

    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
        ){
        Button(
            onClick = { navController.navigate(Navigate.InputPhoneNumber.route) },
            modifier = Modifier
                .padding(24.dp,0.dp,24.dp,24.dp)
                .height(56.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            Text(
                text = stringResource(R.string.welcome_signin_button_text),
                style = MaterialTheme.typography.h3
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    MyApplicationTheme {
        Welcome(
            navController = rememberNavController()
        )
    }
}