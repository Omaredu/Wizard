package com.omaredu.wizard.core

class Game {
    var village: Village = Village()
    var wizard: Wizard = Wizard()
    var player: Player = Player()
    var ogre: Ogre = Ogre()

    private var healthCounter: Int = 0

    fun play() {}

    fun buildHouse() {
        village.build()
    }

    fun randomAttack(): Boolean {
        val attack = (0..100).random() < 50
        if (attack)
            player.health -= 1

        return attack
    }

    fun stealFairy(): Boolean {
        if (village.availableHouses() <= 0) return false
        val fairy = wizard.stealFairy() ?: return false

        val out = village.useHouse(fairy)

        if (out) healthCounter++
        if (healthCounter == 10) {
            player.health += 1
            healthCounter = 0
        }

        return out
    }
}