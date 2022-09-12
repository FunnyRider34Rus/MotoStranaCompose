package com.example.myapplication.ui.screens.messages

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication.App.Companion.cityName
import com.example.myapplication.App.Companion.stateName
import com.example.myapplication.R
import com.example.myapplication.common.AnimatedIndicator
import com.example.myapplication.common.ShowLoading
import com.example.myapplication.ui.screens.BottomNavigationMenu
import com.example.myapplication.ui.screens.messages.model.MessagesEvent
import com.example.myapplication.ui.screens.messages.model.MessagesViewState
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.golbat_10
import com.example.myapplication.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MissingPermission")
@Composable
fun Messages(
    navController: NavController,
    viewModel: MessagesViewModel = viewModel()) {

    //State
    val viewState = viewModel.viewState.observeAsState(MessagesViewState())
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberLazyListState()
    //Tabs
    var tabIndex by rememberSaveable { mutableStateOf(0) }
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    when (tabIndex) {
        0 -> viewModel.obtainEvent(MessagesEvent.StateClicked(stateName))
        1 -> viewModel.obtainEvent(MessagesEvent.CityClicked(cityName))
    }

    val titles = listOf(stateName, cityName)
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        AnimatedIndicator(
            tabPositions = tabPositions,
            selectedTabIndex = tabIndex
        )
    }

    with(viewState.value) {
        if (isLoading) ShowLoading()
        Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = { BottomNavigationMenu(navController = navController) },
            snackbarHost = { scaffoldState.snackbarHostState }
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                //Табсы с чатом области или города
                TabRow(
                    selectedTabIndex = tabIndex,
                    backgroundColor = white,
                    indicator = indicator
                ) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            selected = tabIndex == index,
                            onClick = {
                                tabIndex = index
                            },
                            text = {
                                Text(
                                    text = title,
                                    color = black
                                )
                            }
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    state = scrollState,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items(messages.size) { item ->

                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_add_media),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { },
                    )
                    OutlinedTextField(
                        value = textState,
                        onValueChange = {
                                        textState = it
                        },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .height(56.dp)
                            .weight(1f),
                        placeholder = {
                            Box(
                                modifier = Modifier
                                .fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    textAlign = TextAlign.Start,
                                    maxLines = 1,
                                    style = MaterialTheme.typography.h3,
                                    text = "Сообщение"
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true,
                        shape = MaterialTheme.shapes.large,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = black,
                            backgroundColor = golbat_10,
                            focusedBorderColor = golbat_10,
                            unfocusedBorderColor = golbat_10
                        )
                    )
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_send),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { },
                    )
                }
            }
        }
    }
}