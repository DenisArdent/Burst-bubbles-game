package navigation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import navigation.game.GameComponent
import navigation.start.StartComponent


interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class Start(val component: StartComponent) : Child()
        class Game(val component: GameComponent) : Child()
    }
}