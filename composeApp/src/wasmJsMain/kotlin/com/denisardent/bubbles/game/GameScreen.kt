package com.denisardent.bubbles.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.browser.window
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import navigation.game.GameComponent

@Composable
fun GameContent(
    component: GameComponent
) {
    BurstBubblesGame(
        viewModel = GameViewModel(),
        onLose = component::onLose
    )
}

@Composable
fun BurstBubblesGame(
    viewModel: GameViewModel,
    onLose: (Int) -> Unit
) {
    var score by remember { mutableStateOf(0) }
    val isGameLost by remember {
        derivedStateOf {
            viewModel.bubbles.size >= 10
        }
    }
    val scope = rememberCoroutineScope()
    val bubbles = remember { viewModel.bubbles }

    // Start growing bubbles
    LaunchedEffect(Unit) {
        val screenWidth = window.innerWidth.toFloat()
        val screenHeight = window.innerHeight.toFloat()
        scope.launch {
            for (i in 700 downTo 1 step 10) {
                viewModel.generateBubble(screenWidth, screenHeight)
                delay(i.toLong())
            }
        }
    }

    if (!isGameLost) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text("Score: $score")
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Draw each bubble
                for (bubbleEntry in bubbles) {
                    BubbleView(bubble = bubbleEntry.value, onClick = {
                        viewModel.popBubble(bubbleEntry.key)
                        score++
                    })
                }
            }
        }
    } else {
        onLose(score)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BubbleView(bubble: Bubble, onClick: () -> Unit) {
    val sizeInDp = with(LocalDensity.current) { bubble.size.toDp() }
    Box(
        modifier = Modifier
            .size(sizeInDp)
            .offset(x = bubble.positionX.dp, y = bubble.positionY.dp)
            .onClick { onClick() }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(bubble.color)
        }
    }
}
