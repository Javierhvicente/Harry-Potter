package models

class Dementor(nombre: String = "models.Dementor", contraAtaque: Int = 10) : Enemigo(nombre, contraAtaque) {
    override fun contraAtaque(): Int {
        println("El $nombre contraataca con un hechizo poco eficaz")
        return contraAtaque
    }
}