package com.rainbowt.meditationui.model


import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Feature(
    val title: String,
    @DrawableRes val iconID: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color,
)
