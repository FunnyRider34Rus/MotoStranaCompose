package com.example.motostranacompose.ui.dashboard.detail

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.motostranacompose.core.components.TopAppBar
import com.example.motostranacompose.ui.dashboard.list.DashboardListViewModel
import com.example.motostranacompose.ui.dashboard.list.DashboardListViewState

@Composable
fun ScreenDashboardDetail(
    navController: NavController,
    listViewModel: DashboardListViewModel = hiltViewModel(),
    id: String
) {
    listViewModel.getDashboardContentByKey(key = id)
    val viewState = listViewModel.viewState.collectAsState(DashboardListViewState())

    Column {
        TopAppBar(
            modifier = Modifier,
            text = viewState.value.content?.title.toString(),
            navigationAction = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(ButtonDefaults.ContentPadding)
                        .clickable { navController.navigateUp() }
                )
            }
        )

        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadData(viewState.value.content?.body.toString(), "text/html; charset=UTF-8", null)
            }
        },
            update = { it.loadData(viewState.value.content?.body.toString(), "text/html; charset=UTF-8", null)}
        )
    }
}