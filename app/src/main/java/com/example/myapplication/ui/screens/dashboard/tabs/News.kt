package com.example.myapplication.ui.screens.dashboard.tabs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.database.NODE_NEWS
import com.example.myapplication.ui.screens.dashboard.DashboardViewModel

@Composable
fun News(model: DashboardViewModel = viewModel()) {
    val news = model.getEvent(NODE_NEWS)
    LazyColumn(contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)) {

    }
}