package com.thomas.applicationandroid


abstract class Piece() {
    abstract val largeur: Double
    abstract val longeur: Double
    abstract val nom: String

    open fun surface(): Double {
        val surface = largeur * longeur
        return surface
    }
}

class Cuisine(
    override var largeur: Double = 7.55,
    override var longeur: Double = 5.5,
    override val nom: String = "Cuisine"
) : Piece() {
}

class Salon(
    override val largeur: Double = 4.2,
    override val longeur: Double = 5.2,
    override val nom: String = "Salon"
) : Piece() {
}

fun main() {
    Cuisine.()
    Salon()
}
