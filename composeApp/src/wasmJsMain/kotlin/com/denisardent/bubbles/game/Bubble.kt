package com.denisardent.bubbles.game

import androidx.compose.ui.graphics.Color


data class Bubble(
    var size: Float,
    val positionX: Float,
    val positionY: Float,
    val color: Color = listOf(Color.Blue, Color.Black, Color.Cyan, Color.Red, Color.Magenta).shuffled().first()
)

