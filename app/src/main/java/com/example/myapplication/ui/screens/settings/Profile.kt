package com.example.myapplication.ui.screens.settings

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.common.TextInput
import com.example.myapplication.common.initUser
import com.example.myapplication.database.firebase.*
import com.example.myapplication.ui.navigation.BottomNavItem
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Profile(navController: NavController) {

    val scope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    val fullnameList = USER.fullname.split(" ")
    var userFirstName by remember { mutableStateOf(TextFieldValue(text = fullnameList[0])) }
    var userSecondName by remember { mutableStateOf(TextFieldValue(text = fullnameList[1])) }
    var userName by remember { mutableStateOf(TextFieldValue(text = USER.username)) }
    var userBike by remember { mutableStateOf(TextFieldValue(text = USER.bike)) }
    var isDataChange: Boolean

    //Получение ссылки на локальное фото
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        val path = REMOTE_STORAGE.child(FOLDER_PROFILE_AVATAR).child(USER.id)
            .child(UUID.randomUUID().toString())
        var mediaUrlFromBase: String
        if (uri != null) {
            path.putFile(uri).addOnCompleteListener {
                path.downloadUrl.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        mediaUrlFromBase = task.result.toString()
                        REMOTE_DATABASE.child(NODE_USERS).child(USER.id).child(CHILD_AVATAR)
                            .setValue(mediaUrlFromBase)
                        isDataChange = true
                    }
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_back),
                        contentDescription = stringResource(R.string.button_back_description),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                navController.navigate(BottomNavItem.Settings.route)
                            },
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_button_edit),
                        contentDescription = stringResource(R.string.button_edit_description),
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                isDataChange = false
                                scope.launch {
                                    val currentID = AUTH.currentUser?.uid
                                    if (userFirstName.text.isNotBlank() && userSecondName.text.isNotBlank()) {
                                        val fullname =
                                            "${userFirstName.text} ${userSecondName.text}"
                                        if (fullname != USER.fullname) {
                                            REMOTE_DATABASE
                                                .child(NODE_USERS)
                                                .child(currentID.toString())
                                                .child(CHILD_FULLNAME)
                                                .setValue(fullname)
                                            isDataChange = true
                                        }
                                    }
                                    if (userName.text != USER.username) {
                                        REMOTE_DATABASE
                                            .child(NODE_NICKNAMES)
                                            .addListenerForSingleValueEvent(object :
                                                ValueEventListener {
                                                override fun onDataChange(snapshot: DataSnapshot) {
                                                    if (snapshot.hasChild(userName.text)) {
                                                        scope.launch {
                                                                scaffoldState.snackbarHostState.showSnackbar(
                                                                    message = "Такой псевдоним уже занят"
                                                                )
                                                        }
                                                    } else {
                                                        //удаляем старую запись
                                                        REMOTE_DATABASE
                                                            .child(NODE_NICKNAMES)
                                                            .child(USER.username)
                                                            .removeValue()
                                                        //сохраняем новую запись
                                                        REMOTE_DATABASE
                                                            .child(NODE_NICKNAMES)
                                                            .child(userName.text)
                                                            .setValue(currentID.toString())
                                                        REMOTE_DATABASE
                                                            .child(NODE_USERS)
                                                            .child(currentID.toString())
                                                            .child(CHILD_USERNAME)
                                                            .setValue(userName.text)
                                                        isDataChange = true
                                                    }
                                                }

                                                override fun onCancelled(error: DatabaseError) {

                                                }
                                            })
                                    }
                                    if (userBike.text != USER.bike) {
                                        REMOTE_DATABASE
                                            .child(NODE_USERS)
                                            .child(currentID.toString())
                                            .child(CHILD_BIKE)
                                            .setValue(userBike.text)
                                        isDataChange = true
                                    }
                                }
                                scope.launch {
                                    if (isDataChange) {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Изменения успешно сохранены"
                                        )
                                        initUser()
                                    }
                                }
                            },
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Профиль",
                        modifier = Modifier.padding(top = 40.dp),
                        style = MaterialTheme.typography.h1
                    )
                }
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = USER.avatar.ifBlank {
                                R.drawable.ic_no_profile
                            },
                            placeholder = painterResource(R.drawable.ic_no_profile),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(shape = MaterialTheme.shapes.small)
                                .clickable {
                                    isDataChange = false
                                    launcher.launch("image/*")
                                }
                        )
                    }
                    TextInput(
                        value = userFirstName,
                        hint = "Имя",
                        onValueChange = { userFirstName = it })
                    TextInput(
                        value = userSecondName,
                        hint = "Фамилия",
                        onValueChange = { userSecondName = it })
                    TextInput(
                        value = userName,
                        hint = "Псевдоним",
                        onValueChange = { userName = it })
                    TextInput(
                        value = userBike,
                        hint = "Модель мотоцикла",
                        onValueChange = { userBike = it })
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MyApplicationTheme {
        Profile(navController = rememberNavController())
    }
}