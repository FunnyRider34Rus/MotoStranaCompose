package com.example.motostranacompose.core.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.motostranacompose.R

@Composable
fun ShowError(text: String) {
    val openDialog = remember { mutableStateOf(false)  }
    AlertDialog(
        title = { Text(text = stringResource(id = R.string.alertdialog_title)) },
        text = { Text(text = text) },
        onDismissRequest = {
            openDialog.value = true
        },
        dismissButton = {

        },
        confirmButton = {}
    )
}