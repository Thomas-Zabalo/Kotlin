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


class Etudiant(val name: String, val promo: String, val matieres: List<String>)

val etudiants = listOf(
    Etudiant("Paul", "2025", listOf("mobile", "web", "BDD")),
    Etudiant("Yazid", "2024", listOf("mobile", "Android", "Réseau")),
    Etudiant("Caroline", "2025", listOf("SE", "Anglais")),
)

fun main() {
    Cuisine()
    Salon()

    val promo2024 = etudiants.filter { it.promo == "2024" }
        .forEach { (println("Affichage des étudiants étant de la promotion 2024 " + it.name)) }

    val matSup2 = etudiants.filter { it.matieres.size > 2 }
        .forEach { (println("Affichage des étudiants ayant choisi plus de 2 matière" + it.name)) }
}