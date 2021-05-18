package com.omaredu.wizard

import com.omaredu.wizard.core.Game
import com.omaredu.wizard.io.Ui

fun main() {
    val game: Game = Game()
    val ui: Ui = Ui(game = game)

    ui.init()
}