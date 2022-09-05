package com.example.myapplication.common

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.white

@Composable
fun Indicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(
                width = 126.dp,
                color = black,
                shape = MaterialTheme.shapes.small
            ),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Divider(
            modifier = Modifier
                .height(2.dp)
                .border(
                    width = 26.dp,
                    color = black,
                    shape = RoundedCornerShape(2.dp)
                ),
            color = white
        )
    }
}

@Composable
fun AnimatedIndicator(tabPositions: List<TabPosition>, selectedTabIndex: Int) {
    val transition = updateTransition(selectedTabIndex, label = "")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 50f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        }, label = ""
    ) {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 50f)
            }
        }, label = ""
    ) {
        tabPositions[it].right
    }

    Indicator(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorStart + 8.dp)
            .width((indicatorEnd - 16.dp) - indicatorStart)
    )
}