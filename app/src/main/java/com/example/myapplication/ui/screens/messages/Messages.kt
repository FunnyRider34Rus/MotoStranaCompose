package com.example.myapplication.ui.screens.messages

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myapplication.App.Companion.cityName
import com.example.myapplication.App.Companion.stateName
import com.example.myapplication.R
import com.example.myapplication.common.AnimatedIndicator
import com.example.myapplication.common.ShowLoading
import com.example.myapplication.database.firebase.AUTH
import com.example.myapplication.ui.screens.BottomNavigationMenu
import com.example.myapplication.ui.screens.messages.model.MessagesEvent
import com.example.myapplication.ui.screens.messages.model.MessagesViewState
import com.example.myapplication.ui.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MissingPermission")
@Composable
fun Messages(
    navController: NavController,
    viewModel: MessagesViewModel = viewModel()
) {
    //State
    val viewState = viewModel.viewState.observeAsState(MessagesViewState())
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberLazyListState()
    //Tabs
    var tabIndex by rememberSaveable { mutableStateOf(0) }
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    var location by rememberSaveable { mutableStateOf("") }

    //Получение ссылки на локальное фото
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        viewModel.obtainEvent(
            MessagesEvent.SendImageClicked(
                location,
                uri
            )
        )
    }

    when (tabIndex) {
        0 -> {
            location = stateName.value
            viewModel.obtainEvent(MessagesEvent.TabsClicked(stateName.value))
        }
        1 -> {
            location = stateName.value
            viewModel.obtainEvent(MessagesEvent.TabsClicked(cityName.value))
        }
    }

    val titles = listOf(stateName.value, cityName.value)
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        AnimatedIndicator(
            tabPositions = tabPositions,
            selectedTabIndex = tabIndex
        )
    }

    with(viewState.value) {
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
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    state = scrollState,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    items(messages.size) { item ->
                        //Скролл до нового сообщения
                        LaunchedEffect(messages.size) {
                            scrollState.animateScrollToItem(messages.size - 1)
                        }
                        //Отображение сообщений текущего пользователя
                        if (messages[item]?.uid == AUTH.currentUser?.uid) {
                            //Сообщения текущего пользователя
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillParentMaxWidth(),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.End
                            ) {
                                Card(
                                    backgroundColor = golbat_5
                                ) {
                                    ConstraintLayout {
                                        val (header, image, body) = createRefs()
                                        createVerticalChain(
                                            header,
                                            image,
                                            body,
                                            chainStyle = ChainStyle.Spread
                                        )
                                        Text(
                                            text = messages[item]?.fullname.toString(),
                                            modifier = Modifier
                                                .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                                                .constrainAs(header) {
                                                    top.linkTo(parent.top)
                                                    start.linkTo(parent.start)
                                                },
                                            color = buldasaur_140,
                                            textAlign = TextAlign.Start,
                                            style = MaterialTheme.typography.h4
                                        )
                                        if (!messages[item]?.mediaUrl.isNullOrBlank()) {
                                            AsyncImage(
                                                model = messages[item]?.mediaUrl,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .padding(8.dp)
                                                    .constrainAs(image) {
                                                        top.linkTo(header.bottom)
                                                        start.linkTo(header.start)
                                                    }
                                                    .heightIn(min = 0.dp, max = 180.dp)
                                                    .widthIn(min = 0.dp, max = 132.dp)
                                                    .clip(RoundedCornerShape(14.dp))
                                                    .clickable {
                                                        viewModel.obtainEvent(
                                                            MessagesEvent.FullImageMode(
                                                                messages[item]?.mediaUrl
                                                            )
                                                        )
                                                    },
                                                alignment = Alignment.CenterStart,
                                                contentScale = ContentScale.FillHeight
                                            )
                                        }
                                        if (!messages[item]?.text.isNullOrBlank()) {
                                            Text(
                                                text = messages[item]?.text.toString(),
                                                modifier = Modifier
                                                    .padding(
                                                        start = 8.dp,
                                                        end = 8.dp,
                                                        bottom = 8.dp
                                                    )
                                                    .constrainAs(body) {
                                                        top.linkTo(image.bottom)
                                                        start.linkTo(parent.start)
                                                        bottom.linkTo(parent.bottom)
                                                    },
                                                style = MaterialTheme.typography.h2
                                            )
                                        }
                                    }
                                }
                                Text(
                                    text = messages[item]?.time.toString(),
                                    modifier = Modifier
                                        .padding(2.dp),
                                    style = MaterialTheme.typography.h5
                                )
                            }

                        } else {
                            //Отображение сообщений других пользователей
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    AsyncImage(
                                        model = messages[item]?.avatar,
                                        contentDescription = null,
                                        placeholder = painterResource(R.drawable.ic_no_profile),
                                        modifier = Modifier
                                            .size(44.dp)
                                            .clip(RoundedCornerShape(12.dp)),
                                        alignment = Alignment.Center,
                                        contentScale = ContentScale.Fit
                                    )
                                    Column(
                                        modifier = Modifier.padding(start = 8.dp)
                                    ) {
                                        Card(
                                            backgroundColor = golbat_5
                                        ) {
                                            ConstraintLayout {
                                                val (header, image, body) = createRefs()
                                                createVerticalChain(
                                                    header,
                                                    image,
                                                    body,
                                                    chainStyle = ChainStyle.Spread
                                                )
                                                Text(
                                                    text = messages[item]?.fullname.toString(),
                                                    modifier = Modifier
                                                        .padding(
                                                            start = 8.dp,
                                                            top = 8.dp,
                                                            end = 8.dp
                                                        )
                                                        .constrainAs(header) {
                                                            top.linkTo(parent.top)
                                                            start.linkTo(parent.start)
                                                        },
                                                    color = krabby_140,
                                                    textAlign = TextAlign.Start,
                                                    style = MaterialTheme.typography.h4
                                                )
                                                if (!messages[item]?.mediaUrl.isNullOrBlank()) {
                                                    AsyncImage(
                                                        model = messages[item]?.mediaUrl,
                                                        contentDescription = null,
                                                        modifier = Modifier
                                                            .padding(8.dp)
                                                            .constrainAs(image) {
                                                                top.linkTo(header.bottom)
                                                                start.linkTo(header.start)
                                                            }
                                                            .heightIn(min = 0.dp, max = 180.dp)
                                                            .widthIn(min = 0.dp, max = 132.dp)
                                                            .clip(RoundedCornerShape(14.dp))
                                                            .clickable {
                                                                viewModel.obtainEvent(
                                                                    MessagesEvent.FullImageMode(
                                                                        messages[item]?.mediaUrl
                                                                    )
                                                                )
                                                            },
                                                        alignment = Alignment.CenterStart,
                                                        contentScale = ContentScale.FillHeight
                                                    )
                                                }
                                                if (!messages[item]?.text.isNullOrBlank()) {
                                                    Text(
                                                        text = messages[item]?.text.toString(),
                                                        modifier = Modifier
                                                            .padding(
                                                                start = 8.dp,
                                                                end = 8.dp,
                                                                bottom = 8.dp
                                                            )
                                                            .constrainAs(body) {
                                                                top.linkTo(image.bottom)
                                                                start.linkTo(parent.start)
                                                                bottom.linkTo(parent.bottom)
                                                            },
                                                        style = MaterialTheme.typography.h2
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                                Text(
                                    text = messages[item]?.time.toString(),
                                    modifier = Modifier.padding(2.dp),
                                    style = MaterialTheme.typography.h5
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_add_media),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                launcher.launch("image/*")
                            },
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
                            backgroundColor = golbat_5,
                            focusedBorderColor = golbat_5,
                            unfocusedBorderColor = golbat_5
                        )
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_send),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                if (textState.text.isNotBlank()) {
                                    viewModel.obtainEvent(
                                        MessagesEvent.SendMessageClicked(
                                            location,
                                            textState.text
                                        )
                                    )
                                    textState = TextFieldValue("")
                                }
                            }
                    )
                }
            }
            //Обработка полноэкранного открытия изображения
            if (isFullMode) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 86.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    if (!imageUri.isNullOrBlank()) {
                        AsyncImage(
                            model = imageUri,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .clip(RoundedCornerShape(14.dp)),
                            alignment = Alignment.CenterStart,
                            contentScale = ContentScale.FillBounds
                        )
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_close),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(32.dp)
                                .clickable {
                                    viewModel.obtainEvent(MessagesEvent.FullImageMode(imageUri))
                                }
                        )
                    }
                }
            }
            //Экран загрузки
            if (isLoading) ShowLoading()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessagesPreview() {
    MyApplicationTheme {
        Messages(navController = rememberNavController(), viewModel = MessagesViewModel())
    }
}



