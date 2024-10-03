package com.denisardent.bubbles.game

import androidx.compose.runtime.mutableStateMapOf
import kotlin.random.Random

class GameViewModel {
    var bubbles = mutableStateMapOf<Int, Bubble>()
        private set

    private var bubbleId = 0

    fun generateBubble(screenWidth: Float, screenHeight: Float) {
        bubbles[bubbleId++] = Bubble(
            size = 50f,
            positionX = Random.nextFloat() * (screenWidth - 100f), // Random X position within screen width
            positionY = Random.nextFloat() * (screenHeight - 100f)
        )
    }

    fun popBubble(bubbleId: Int) {
        bubbles.remove(bubbleId)
    }
}