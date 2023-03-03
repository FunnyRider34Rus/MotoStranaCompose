package com.example.motostranacompose.ui.dashboard.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.motostranacompose.core.components.TopAppBar
import com.example.motostranacompose.data.model.DashboardContent
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.transformation.blur.BlurTransformationPlugin

@Composable
fun DashboardList(modifier: Modifier, content: DashboardContent) {
    Column(
        modifier = modifier
    ) {

        TopAppBar(
            modifier = Modifier.wrapContentHeight(Alignment.Top),
            text = content.title.toString()
        )

        ContentBody(
            modifier = Modifier.weight(1f),
            content = content
        )

        Footer(
            modifier = Modifier.wrapContentHeight(Alignment.Bottom)
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
                +BlurTransformationPlugin(radius = 16) // between 0 to Int.MAX_VALUE.
            },
            imageOptions = ImageOptions(alignment = Alignment.Center, contentScale = ContentScale.FillHeight)
        )
        Image(
            painter = painter,
            contentDescription = content.title,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.None
        )
        Text(
            text = content.header.toString(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp),
            color = Color.White
        )
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(ButtonDefaults.IconSpacing)
    ) {
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = null,
            modifier = modifier.padding(ButtonDefaults.IconSpacing)
        )
        Text(
            text = "0",
            modifier = modifier.padding(vertical = ButtonDefaults.IconSpacing),
            textAlign = TextAlign.Center
        )
        Icon(
            imageVector = Icons.Outlined.ChatBubbleOutline,
            contentDescription = null,
            modifier = modifier.padding(ButtonDefaults.IconSpacing)
        )
        Text(
            text = "0",
            modifier = modifier.padding(vertical = ButtonDefaults.IconSpacing),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = null,
            modifier = modifier.padding(ButtonDefaults.IconSpacing)
        )
    }
}

@Preview
@Composable
fun DashboardListPreview() {
    DashboardList(modifier = Modifier, content = DashboardContent())
}