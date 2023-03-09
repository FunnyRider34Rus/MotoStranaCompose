package com.example.motostranacompose.ui.dashboard.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.motostranacompose.core.components.TopAppBar
import com.example.motostranacompose.core.timestampToDate
import com.example.motostranacompose.data.model.DashboardContent
import com.example.motostranacompose.data.model.DashboardType
import com.example.motostranacompose.navigation.Screen
import com.example.motostranacompose.ui.dashboard.list.DashboardListEvent
import com.example.motostranacompose.ui.dashboard.list.DashboardListViewModel
import com.example.motostranacompose.ui.dashboard.list.LikeStatus
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.transformation.blur.BlurTransformationPlugin

@Composable
fun DashboardListItem(
    navController: NavController,
    modifier: Modifier,
    content: DashboardContent
) {
    val key = content.key
    Column(
        modifier = modifier
    ) {

        TopAppBar(
            modifier = Modifier.wrapContentHeight(Alignment.Top),
            text = content.title.toString(),
            { },
            { }
        )

        ContentBody(
            modifier = Modifier
                .weight(1f)
                .clickable { navController.navigate(Screen.DASHDETAIL.route + "/$key") },
            content = content
        )

        Footer(
            modifier = Modifier.wrapContentHeight(Alignment.CenterVertically),
            content = content
        )
    }
}

@Composable
fun ContentBody(modifier: Modifier, content: DashboardContent) {
    val painter = rememberAsyncImagePainter(content.image)
    Box(modifier = modifier) {
        CoilImage(
            imageModel = { content.image },
            modifier = Modifier.fillMaxSize(),
            component = rememberImageComponent {
                +BlurTransformationPlugin(radius = 32)
            },
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentScale = ContentScale.FillHeight
            )
        )
        Image(
            painter = painter,
            contentDescription = content.title,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit
        )
        Text(
            text = content.header.toString(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 32.dp),
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = when (content.type) {
                DashboardType.NEWS.toString() -> "#новость"
                DashboardType.POST.toString() -> "#пост"
                else -> ""
            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(ButtonDefaults.TextButtonContentPadding),
            color = Color.White,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = timestampToDate(content.timestamp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(ButtonDefaults.TextButtonContentPadding),
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun Footer(
    modifier: Modifier,
    content: DashboardContent,
    viewModel: DashboardListViewModel = hiltViewModel()
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                viewModel.obtainEvent(DashboardListEvent.LikeClick(content = content))
            },
        ) {
            Icon(
                imageVector = when (viewModel.getLikeStatus(content.likes)) {
                    LikeStatus.UNLIKE -> Icons.Filled.Favorite
                    LikeStatus.NONE -> Icons.Outlined.FavoriteBorder
                    LikeStatus.LIKE -> Icons.Filled.Favorite
                },
                contentDescription = null,
                tint = if (viewModel.getLikeStatus(content.likes) == LikeStatus.LIKE) Color.Red else Color.Black
            )
        }
        Text(
            text = if (content.likes.isNullOrEmpty()) "0" else content.likes.size.toString()
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.ChatBubbleOutline,
                contentDescription = null
            )
        }
        Text(
            text = "0"
        )
        Spacer(modifier = modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun DashboardListPreview() {
    DashboardListItem(
        navController = rememberNavController(),
        modifier = Modifier,
        content = DashboardContent()
    )
}