package models

class Dementor(nombre: String = "models.Dementor", contraAtaque: Int = 10) : Enemigo(nombre, contraAtaque) {
    private val harry = Harry()
    override fun contraAtaque(): Int {
        println("El $nombre contraataca con un hechizo poco eficaz")
        return contraAtaque
    }
    fun interaccion(mazmorra: Mazmorra ,mazmorraMatriz: Array<Array<Any?>>, fila: Int, columna: Int){
        do {
            val random = (0..100).random()
            if (random > 60) {
                println("Harry ha fallado")
                contraAtaque()
                harry.vida -= contraAtaque
            }else{
                println("Harry acierta el hechizo y ejecuta al dementor")
                mazmorra.dementoresEjecutados++
                mazmorraMatriz[fila][columna] = Harry()
            }

        } while (random > 60 && harry.vida > 0)
    }
}