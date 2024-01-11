package models

class Horrocurxes {
    fun interracción(mazmorra: Mazmorra ,mazmorraMatriz: Array<Array<Any?>>, fila: Int, columna: Int){
        println("Harry ha destruido un horrocrux")
        mazmorra.horrDestruidos++
        mazmorraMatriz[fila][columna] = Harry()
    }
}