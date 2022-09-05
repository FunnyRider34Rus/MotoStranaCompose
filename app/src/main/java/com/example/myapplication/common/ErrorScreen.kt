package com.example.myapplication.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ShowError() {
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
                text = "Пожалуйста, проверьте соединение с интернетом",
                style = MaterialTheme.typography.h2
            )
        },
        confirmButton = {

        }
    )
}