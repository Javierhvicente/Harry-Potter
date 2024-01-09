open class McGonall(nombre: String ="McGonall", vida: Int = 1, var curacion: Int = 70) :Aliado(nombre, vida) {
    var vista: Boolean = false
    fun cambiarVista(vista: Boolean){
        if(!vista){
            this.vista = true
        }else{
            this.vista = false
        }
    }
}