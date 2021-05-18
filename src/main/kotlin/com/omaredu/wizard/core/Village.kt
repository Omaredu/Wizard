package com.omaredu.wizard.core

import java.util.*

class Village {
    private val unusedBuildings: Queue<House> = LinkedList()
    private val usedBuildings: Queue<House> = LinkedList()

    fun build() {
        unusedBuildings.add(House())
    }

    fun useHouse(fairy: Fairy): Boolean {
        if (unusedBuildings.size <= 0)
            return false

        val house = unusedBuildings.remove()
        house.fairy = fairy

        usedBuildings.add(house)
        return true
    }

    fun availableHouses(): Int {
        return unusedBuildings.size
    }

    fun unavailableHouses(): Int {
        return usedBuildings.size
    }
}