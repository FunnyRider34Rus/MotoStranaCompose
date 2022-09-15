package com.example.myapplication.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.golbat_10
import com.example.myapplication.ui.theme.golbat_5

@Composable
fun TextInput(
    value: TextFieldValue,
    hint: String,
    onValueChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
            .clip(MaterialTheme.shapes.large)
            .background(golbat_5),
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        placeholder = {
            Text(
                text = hint,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = black,
            backgroundColor = golbat_10,
            focusedBorderColor = golbat_10,
            unfocusedBorderColor = golbat_10
        )
    )
}