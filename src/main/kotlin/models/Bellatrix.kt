package models

class Bellatrix(nombre: String ="Bellatrix", contraAtaque: Int = 30) : Enemigo(nombre, contraAtaque), Visibilidad {
    var vista: Boolean = false
    private val harry = Harry()
    override fun cambiarVista(vista: Boolean) {
        if(!vista){
            this.vista = true
        }
    }
    fun teletransporte(mazmorra: Array<Array<Any?>>): Array<Array<Any?>> {
        var fila: Int
        var columna: Int
        do {
            fila = (0..mazmorra.size - 1).random()
            columna = (0..mazmorra.size - 1).random()
        } while (mazmorra[fila][columna] != null && mazmorra[fila][columna] != true)
        when(mazmorra[fila][columna]){
            true -> cambiarVista(vista)
        }
        mazmorra[fila][columna] = this
        return mazmorra
    }

    fun interacción(mazmorra: Array<Array<Any?>>, fila: Int, columna:Int){
        println("Harry se ha encontrado a Bellatrix")
        do {
            val random = (0..100).random()
            if (random > 60) {
                println("Harry ha fallado")
                contraAtaque()
                harry.vida -= contraAtaque
            }else{
                println("Harry acierta el hechizo y Bellatrix huye")
                mazmorra[fila][columna] = Harry()
                teletransporte(mazmorra)
            }
        } while (random > 60 && harry.vida > 0)
    }
}