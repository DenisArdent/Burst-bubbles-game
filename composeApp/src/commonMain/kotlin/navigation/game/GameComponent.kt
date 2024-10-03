package navigation.game

import com.arkivanov.decompose.value.Value

interface GameComponent {

    fun onLose(score: Int)
}