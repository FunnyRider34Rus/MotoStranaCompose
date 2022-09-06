package com.example.myapplication.ui.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.screens.dashboard.model.DashboardViewState
import com.example.myapplication.ui.theme.golbat_60

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardEventDetailScreen(
    navController: NavController,
    viewModel: DashboardViewModel = viewModel()
) {

    val viewState = viewModel.viewState.observeAsState(DashboardViewState())

    with(viewState.value) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_back),
                contentDescription = stringResource(R.string.button_back_description),
                modifier = Modifier
                    .padding(top = 32.dp)
                    .size(24.dp)
                    .clickable { navController.popBackStack() },
            )
            Text(
                text = itemValue?.title_text.toString(),
                modifier = Modifier.padding(top = 40.dp),
                style = MaterialTheme.typography.h1
            )
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                AsyncImage(
                    model = itemValue?.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = itemValue?.date.toString(),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = itemValue?.header_text.toString(),
                    modifier = Modifier.padding(vertical = 16.dp),
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = itemValue?.body_text.toString(),
                    color = golbat_60,
                    style = MaterialTheme.typography.h3
                )
            }
        }
    }
}