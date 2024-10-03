package navigation.root
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import navigation.game.GameComponent
import navigation.game.SimpleGameComponent
import navigation.start.SimpleStartComponent
import navigation.start.StartComponent

class SimpleRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Start(),
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(config: Config, childComponentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.Start -> RootComponent.Child.Start(startComponent(childComponentContext, config.lastScore))
            is Config.Game -> RootComponent.Child.Game(gameComponent(childComponentContext))
        }

    private fun startComponent(
        componentContext: ComponentContext,
        lastScore: Int
    ): StartComponent =
        SimpleStartComponent(
            componentContext = componentContext,
            score = lastScore,
            onStart = {
                navigation.pop()
                navigation.push(Config.Game)
            }
        )

    private fun gameComponent(componentContext: ComponentContext): GameComponent =
        SimpleGameComponent(
            componentContext = componentContext,
            onFinished = {
                navigation.pop()
                navigation.push(Config.Start(it))
            },
        )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data class Start(val lastScore: Int = 0) : Config

        @Serializable
        data object Game : Config
    }
}