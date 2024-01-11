package models

class Hermione(nombre: String = "Hermione", vida: Int = 1, var curacion: Int = 30) : Aliado(nombre, vida), Visibilidad {
    var vista: Boolean = false
    private val harry = Harry()
    override fun cambiarVista(vista: Boolean){
        if(!vista){
            this.vista = true
        }
    }
    fun interacción(mazmorra: Array<Array<Any?>>, fila: Int, columna: Int){
        if (harry.vida >= 100) {
            println("Harry ya tienes toda la vida lokete")
            mazmorra[fila][columna] = Hermione()
            cambiarVista(vista)
        } else {
            println("Hermione cura las heridas de Harry con un hechizo")
            harry.vida += curacion
            mazmorra[fila][columna] = Harry()
        }
    }
}