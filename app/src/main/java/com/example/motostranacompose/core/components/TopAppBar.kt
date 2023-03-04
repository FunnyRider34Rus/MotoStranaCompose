package com.example.motostranacompose.core.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopAppBar(modifier: Modifier, text: String, navigationAction: (@Composable() (() -> Unit))?) {
    TopAppBar(
        title = {
                Text(
                    text = text,
                    style = MaterialTheme.typography.h6
                )
        },
        modifier = modifier,
        navigationIcon = navigationAction,
        backgroundColor = Color.White,
        contentColor = Color.Black
    )
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar(modifier = Modifier, text = "Title", null)
}