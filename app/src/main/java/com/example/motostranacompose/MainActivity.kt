@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.motostranacompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.motostranacompose.core.Navigation
import com.example.motostranacompose.ui.theme.MotoStranaComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotoStranaComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun MainScreenContainer() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.app_logo_dark),
                        contentDescription = "Application logo",
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp)
                            .size(40.dp)
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Button Add",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(32.dp)
                    )
                }
            )
        }, content = { innerPadding -> Box(modifier = Modifier.padding(innerPadding))}
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MotoStranaComposeTheme {
        MainScreenContainer()
    }
}