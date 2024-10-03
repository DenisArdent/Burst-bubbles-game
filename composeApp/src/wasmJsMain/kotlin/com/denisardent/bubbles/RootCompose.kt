package com.denisardent.bubbles

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.denisardent.bubbles.game.GameContent
import navigation.root.RootComponent

@Composable
fun RootCompose(
    component: RootComponent
) {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.systemBars)) {
            Children(
                stack = component.stack,
                modifier = Modifier.fillMaxSize(),
                animation = stackAnimation(fade())
            ) {
                when (val instance = it.instance) {
                    is RootComponent.Child.Start -> StartComponent(instance.component)
                    is RootComponent.Child.Game -> GameContent(instance.component)
                }
            }
        }
    }
}