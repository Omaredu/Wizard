package com.omaredu.wizard.io

import com.omaredu.wizard.core.Game
import java.lang.NumberFormatException
import kotlin.system.exitProcess

class Ui(val game: Game) {
    var gameEnded: Boolean = false

    // region colors
    val ANSI_RESET = "\u001B[0m"
    val ANSI_BLACK = "\u001B[30m"
    val ANSI_RED = "\u001B[31m"
    val ANSI_GREEN = "\u001B[32m"
    val ANSI_YELLOW = "\u001B[33m"
    val ANSI_BLUE = "\u001B[34m"
    val ANSI_PURPLE = "\u001B[35m"
    val ANSI_CYAN = "\u001B[36m"
    val ANSI_WHITE = "\u001B[37m"
    // endregion

    // region title
    val GAME_TITLE =
        """
          ___       __   ___  ________  ________  ________  ________     
         |\  \     |\  \|\  \|\_____  \|\   __  \|\   __  \|\   ___ \    
         \ \  \    \ \  \ \  \\|___/  /\ \  \|\  \ \  \|\  \ \  \_|\ \   
          \ \  \  __\ \  \ \  \   /  / /\ \   __  \ \   _  _\ \  \ \\ \  
           \ \  \|\__\_\  \ \  \ /  /_/__\ \  \ \  \ \  \\  \\ \  \_\\ \ 
            \ \____________\ \__\\________\ \__\ \__\ \__\\ _\\ \_______\
             \|____________|\|__|\|_______|\|__|\|__|\|__|\|__|\|_______|
                                                               by Omaredu
        """.trimIndent()
    // endregion

    fun init() {
        run()
    }

    fun run() {
        showMainMenu()

        val input = askNumber()

        if (input == 1) {
            while (!gameEnded)
                play()
        } else { endGame() }
    }

    private fun play() {
        if (game.player.isDead()) {
            showError("The Wizard defeated you... Try again and defeat the Wizard!")
            endGame()
        } else if (game.wizard.isDead()) {
            showMessage("You defeated the Wizard, you won!")
            endGame()
        }

        showStatus()
        showOptions()

        val input = askNumber()

        if (input == 1) {
            showMessage("Building new house...")
            game.buildHouse()
        }
        else if (input == 2) {
            if (game.stealFairy())
                showMessage("You stole one of the Wizard's fairies")
            else
                showError("It looks like you don't have enough available houses to do that...")
        }
        else
            endGame()

        if (game.randomAttack())
            showError("Oh no, the Ogre did just attacked you!")
    }

    private fun endGame() {
        println(
                ANSI_PURPLE +
                "Thanks for playing Wizard by Omaredu." +
                ANSI_RESET
        )
        exitProcess(0)
    }

    private fun showMainMenu() {
        println(ANSI_PURPLE + GAME_TITLE + ANSI_RESET)
        println(
                "1. Play" +
                "\n" +
                "2. Exit"
        )
    }

    fun askUser(): String {
        print(ANSI_CYAN + "$ " + ANSI_RESET)
        return readLine() ?: ""
    }

    fun askNumber(): Int {
        return try {
            askUser().toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }

    fun showStatus() {
        print(
                ANSI_YELLOW +
                "Player's HP: ${game.player.health}" + "\n" +
                "Available houses in the village: ${game.village.availableHouses()}" + "\n" +
                "Unavailable houses in the village (saved fairies): ${game.village.unavailableHouses()}" + "\n" +
                "Wizard's PP: ${game.wizard.power}" + "\n" +
                ANSI_RESET
        )
    }

    fun showOptions() {
        print(
                "1. Build a new house" + "\n" +
                "2. Steal a fairy" + "\n" +
                "3. End game" + "\n"
        )
    }

    fun showMessage(message: String) {
        println(ANSI_GREEN + "> " + message + ANSI_RESET)
    }

    fun showError(message: String) {
        println(ANSI_RED + "> " + message + ANSI_RESET)
    }
}