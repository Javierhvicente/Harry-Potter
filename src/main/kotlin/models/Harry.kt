package models

open class Harry(nombre: String ="Harry", vida: Int = 100) : Aliado(nombre, vida) {
    var fila: Int = 0
    var columna: Int = 0
    var nuevaFila: Int = 0
    var nuevaColumna: Int = 0
}