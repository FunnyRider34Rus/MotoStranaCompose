package com.example.motostranacompose.ui.dashboard.detail

import android.content.Context
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.motostranacompose.core.components.TopAppBar

@Composable
fun ScreenDashboardDetail(
    navController: NavController,
    id: String,
    viewModel: DashboardDetailViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewState.collectAsState(DashboardDetailViewState())
    viewModel.getEvent(DashboardDetailEvent.getContentByKey(id))

    Column {
        TopAppBar(
            modifier = Modifier,
            text = viewState.value.content?.title.toString(),
            navigationAction = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            {  }
        )

        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                addJavascriptInterface(WebAppInterface(context), "Android")
                loadData(
                    viewState.value.content?.body.toString(),
                    "text/html; charset=UTF-8",
                    null
                )
            }
        },
            update = {
                it.loadData(
                    viewState.value.content?.body.toString(),
                    "text/html; charset=UTF-8",
                    null
                )
            }
        )
    }
}

class WebAppInterface(private val mContext: Context) {
    /** Show a toast from the web page  */
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }
}