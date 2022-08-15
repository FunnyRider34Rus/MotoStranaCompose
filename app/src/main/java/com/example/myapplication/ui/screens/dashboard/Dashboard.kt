package com.example.myapplication.ui.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.screens.dashboard.tabs.navigation.TabItem
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.white
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Dashboard() {
    val tabs = listOf(
        TabItem.News,
        TabItem.Events
    )
    val pagerState = rememberPagerState(tabs.size)
    Scaffold(
        topBar = {  }) {
        Column {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = white,
        contentColor = black,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            // OR Tab()
            Tab(
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screen()
    }
}
