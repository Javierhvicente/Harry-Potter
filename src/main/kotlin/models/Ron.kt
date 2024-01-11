package models

class Ron(nombre: String = "Ron", vida: Int = 1, var curacion: Int = 20, val fallo: Double = 0.3) : Aliado(nombre, vida), Visibilidad {
    var vista: Boolean = false
    private val harry = Harry()
    override fun cambiarVista(vista: Boolean){
        if(!vista){
            this.vista = true
        }
    }
    fun interacción(mazmorra: Array<Array<Any?>>, fila: Int, columna: Int){
        if (harry.vida >= 100) {
            println("Harry que tienes toda la vida calla ya ")
            mazmorra[fila][columna] = Ron()
            cambiarVista(vista)
        } else {
            if ((0..100).random() < 30) {
                println("Ron ha metido la pata y ha fallado la curación haciendo daño a harry")
                harry.vida -= 10
            } else {
                println("Ron cura las heridas de Harry con un hechizo")
                harry.vida += curacion
                mazmorra[fila][columna] = Harry()
            }
        }
    }
}