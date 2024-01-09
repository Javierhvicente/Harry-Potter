class Hermione(nombre: String = "Hermione", vida: Int = 1, var curacion: Int = 30) :Aliado(nombre, vida) {
    var vista: Boolean = false
    fun cambiarVista(vista: Boolean){
        if(!vista){
            this.vista = true
        }else{
            this.vista = false
        }
    }
}