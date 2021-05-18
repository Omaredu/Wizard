package com.omaredu.wizard.core

class Wizard(
    var power: Int = 2
) {
    private val fairies: MutableList<Fairy> = mutableListOf()

    init {
        for (i in 1..(power*10))
            fairies.add(Fairy())

        power = fairies.size / 10
    }

    fun stealFairy(): Fairy? {
        var fairy: Fairy? = null

        try {
            fairy = fairies.removeLast()
        } catch (e: NoSuchElementException) { }

        power = fairies.size / 10

        return fairy
    }

    fun isDead(): Boolean {
        return power <= 0
    }
}