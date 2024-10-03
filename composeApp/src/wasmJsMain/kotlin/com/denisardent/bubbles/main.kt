package com.denisardent.bubbles

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kotlinx.browser.document
import navigation.root.SimpleRootComponent

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val root = SimpleRootComponent(DefaultComponentContext(LifecycleRegistry()))
    ComposeViewport(document.body!!) {
        App(root)
    }
}