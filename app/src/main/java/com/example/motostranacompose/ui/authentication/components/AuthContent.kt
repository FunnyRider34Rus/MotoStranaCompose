package com.example.motostranacompose.ui.authentication.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.motostranacompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthContent(
    padding: PaddingValues,
    appBarScrollBehavior: TopAppBarScrollBehavior,
    textScrollState: ScrollState,
    isCheck: Boolean,
    onCheckBoxClicked: (Boolean) -> Unit,
    onAuthButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(padding)
    ) {
        Text(
            text = stringResource(id = R.string.auth_body),
            modifier = Modifier
                .verticalScroll(textScrollState)
                .nestedScroll(appBarScrollBehavior.nestedScrollConnection),
            style = MaterialTheme.typography.bodyMedium
        )
        Column(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(modifier = Modifier.padding(vertical = 16.dp)) {
                Checkbox(
                    checked = isCheck,
                    onCheckedChange = onCheckBoxClicked
                )
                Text(
                    text = stringResource(id = R.string.auth_checkbox_text),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Button(
                onClick = onAuthButtonClicked,
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = isCheck,
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_google_logo),
                    contentDescription = "SignIn with Google",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = stringResource(id = R.string.auth_button))
            }
        }
    }
}