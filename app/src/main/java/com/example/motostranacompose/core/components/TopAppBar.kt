package com.example.motostranacompose.core.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier,
    text: String,
    navigationAction: (@Composable () -> Unit),
    actions: (@Composable RowScope.() -> Unit)
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = text,
                modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
        },
        modifier = modifier,
        navigationIcon = navigationAction,
        actions = actions
    )
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar(modifier = Modifier, text = "Title", { }, { })
}