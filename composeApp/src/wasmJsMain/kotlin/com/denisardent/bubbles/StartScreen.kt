package com.denisardent.bubbles

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import navigation.game.GameComponent
import navigation.start.StartComponent

@Composable
fun StartComponent(
    component: StartComponent
) {
    val color = rememberInfiniteTransition(label = "color").animateColor(
        initialValue = Color.Green,
        targetValue = Color.Red,
        animationSpec =
        infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Button(onClick = {
                component.onStartClicked()
            }) {
                Text("Начать игру")
            }
            Text("Your last score: ${component.score}", color = color.value)
        }

    }
}