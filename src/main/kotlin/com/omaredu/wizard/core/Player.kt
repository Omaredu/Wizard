package com.omaredu.wizard.core

class Player(
    var health: Int = 5
) {
    fun isDead(): Boolean {
        return health <= 0
    }
}