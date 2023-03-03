package com.example.motostranacompose.core.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopAppBar(modifier: Modifier, text: String) {
    TopAppBar(
        title = {
            Text(
                text = text,
                modifier = Modifier.fillMaxSize().wrapContentHeight(),
                textAlign = TextAlign.Center
            )
        },
        modifier = modifier,
        backgroundColor = Color.White,
        contentColor = Color.Black
    )
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar(modifier = Modifier,text = "Title")
}