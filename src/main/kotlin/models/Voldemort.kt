package models

open class Voldemort(nombre: String = "models.Voldemort", contraAtaque: Int = 70) : Enemigo(nombre, contraAtaque), Visibilidad {
    override fun contraAtaque(): Int {
        println("$nombre contraataca con un hechizo")
        return 70
    }
    var vista: Boolean = false
    override fun cambiarVista(vista: Boolean){
        if(!vista){
            this.vista = true
        }
    }
}