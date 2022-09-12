package com.example.myapplication.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ShowError(error: Error) {
    lateinit var text: String
    when (error) {
        Error.NONE -> return
        Error.NO_INTERNET -> text = "Отсутствует подключение к интернету"
        Error.NO_LOCATION -> text = "Необходимо предотавить доступ к геопозиции"
        Error.DB_ERROR -> text = "База данных не доступна"
    }
    AlertDialog(
        onDismissRequest = {

        },
        title = {
            Text(
                text = "Ошибка",
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

enum class Error {
    NONE, NO_INTERNET, NO_LOCATION, DB_ERROR
}