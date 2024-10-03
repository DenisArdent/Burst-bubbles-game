package navigation.game

import com.arkivanov.decompose.ComponentContext

class SimpleGameComponent(
    private val componentContext: ComponentContext,
    private val onFinished: (Int) -> Unit,
) : GameComponent, ComponentContext by componentContext {

    override fun onLose(score: Int) {
        onFinished(score)
    }

}