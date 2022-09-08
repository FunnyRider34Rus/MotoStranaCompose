package com.example.myapplication.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ShowError(text: String) {
    AlertDialog(
        onDismissRequest = {

        },
        title = {
            Text(
                text = "Ошибка загрузки данных",
                style = MaterialTheme.typography.h1
            )
        },
        text = {
            Text(
                text = text,
                style = MaterialTheme.typography.h2
            )
        },
        confirmButton = {

        }
    )
}