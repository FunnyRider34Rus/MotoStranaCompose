package com.example.myapplication.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

val Typography = Typography(
        h1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Light,
                fontSize = 32.sp
        ),
        h2 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
        ),
        h3 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        ),
        h4 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
        ),
        h5 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp
        ),
        button = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp
        )
)