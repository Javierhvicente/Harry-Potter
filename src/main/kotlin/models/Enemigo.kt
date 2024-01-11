package models

abstract class Enemigo (var nombre: String = "models.Enemigo", var contraAtaque: Int = 1) {
    open fun contraAtaque(): Int {
        println("$nombre contraataca con un hechizo")
        return contraAtaque
    }
}