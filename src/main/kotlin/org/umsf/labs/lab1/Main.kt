package org.umsf.labs.lab1

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

public fun main() {
    application {
        MaterialTheme {
            Window(
                onCloseRequest = ::exitApplication,
                state = WindowState(size = DpSize(1200.dp, 600.dp))
            ) {
                App()
            }
        }
    }
}
