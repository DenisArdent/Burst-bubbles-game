package navigation.start

import com.arkivanov.decompose.ComponentContext

class SimpleStartComponent(
    componentContext: ComponentContext,
    override val score: Int,
    private val onStart: () -> Unit
): StartComponent, ComponentContext by componentContext {
    override fun onStartClicked() {
        onStart()
    }
}