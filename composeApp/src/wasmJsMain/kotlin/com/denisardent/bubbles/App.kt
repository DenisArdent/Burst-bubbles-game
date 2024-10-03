package com.denisardent.bubbles

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import navigation.root.SimpleRootComponent

@Composable
fun App(rootComponent: SimpleRootComponent) {

    RootCompose(
        rootComponent
    )
}