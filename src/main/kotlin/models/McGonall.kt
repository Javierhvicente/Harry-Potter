package models

open class McGonall(nombre: String ="McGonall", vida: Int = 1, var curacion: Int = 70) : Aliado(nombre, vida), Visibilidad {
    var vista: Boolean = false
    private val harry = Harry()
    override fun cambiarVista(vista: Boolean){
        if(!vista){
            this.vista = true
        }
    }
    fun interacción(mazmorra: Array<Array<Any?>>, fila: Int, columna: Int){
        if (harry.vida >= 100) {
            println("Harry ya tienes toda la vida")
            mazmorra[fila][columna] = McGonall()
            cambiarVista(vista)
        } else {
            println("McGonall cura las heridas de Harry con un hechizo")
            harry.vida += curacion
            mazmorra[fila][columna] = Harry()
        }
    }
}