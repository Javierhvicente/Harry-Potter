open class Voldemort(nombre: String = "Voldemort", contraAtaque: Int = 70) :Enemigo(nombre, contraAtaque) {
    override fun contraAtaque(): Int {
        println("$nombre contraataca con un hechizo")
        return 70
    }
}