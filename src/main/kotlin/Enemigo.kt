open class Enemigo (var nombre: String = "Enemigo", var contraAtaque: Int = 1) {
    open fun contraAtaque(): Int {
        println("$nombre contraataca con un hechizo")
        return contraAtaque
    }
}