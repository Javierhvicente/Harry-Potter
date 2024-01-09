class Ron(nombre: String = "Ron", vida: Int = 1, var curacion: Int = 20, val fallo: Double = 0.3) :Aliado(nombre, vida) {
    var vista: Boolean = false
    fun cambiarVista(vista: Boolean){
        if(!vista){
            this.vista = true
        }else{
            this.vista = false
        }
    }
}