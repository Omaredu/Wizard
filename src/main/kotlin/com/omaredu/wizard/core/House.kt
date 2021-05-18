package com.omaredu.wizard.core

class House() {
    var fairy: Fairy? = null

    fun use(fairy: Fairy) {
        if (inUse())
            return

        this.fairy = fairy
    }

    private fun inUse(): Boolean {
        return fairy != null
    }
}