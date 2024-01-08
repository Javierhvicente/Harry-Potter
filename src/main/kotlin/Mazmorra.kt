class Mazmorra (val mapSize: Int = 6, val numHolo: Int = 7, val numDementors: Int = 6) {
    private var mazmorra = Array(mapSize){ arrayOfNulls<Any>(mapSize)}
    val harry = Harry()
    val horrocurxes = Horrocurxes()
    val dementor = Dementor()
    val voldemort = Voldemort()
    val bellatrix = Bellatrix()
    val mcGonall = McGonall()
    val hermione = Hermione()
    val ron = Ron()
    private var horrDestruidos = 0
    init {
        println("Bienvenido Harry")
        mazmorra[harry.fila][harry.columna] = harry
        mazmorra = inicializarMazmorra(mazmorra)
        printMazmorra(mazmorra)
    }

    fun aventura(){
        do{
            accion()
            printMazmorra(mazmorra)
        }while (horrDestruidos < 7)

    }

    private fun accion(){
        movimiento()
        when (mazmorra[harry.fila][harry.columna]){
            voldemort ->{
                println("Harry se ha encontrado a Voldemort")
                do{
                    val random = (0..100).random()
                    if(random > 60){
                        println("Harry ha fallado")
                        voldemort.contraAtaque()
                        harry.vida -= voldemort.contraAtaque
                    }
                    println("Harry acierta el hechizo y voldemort huye")
                    mazmorra[harry.fila][harry.columna] = harry
                    colocarPosAleatoria(Voldemort(), mazmorra)
                }while (random > 60)
            }
            bellatrix ->{ println("Harry se ha encontrado a Voldemort")
                do{
                    val random = (0..100).random()
                    if(random > 60){
                        println("Harry ha fallado")
                        bellatrix.contraAtaque()
                        harry.vida -= bellatrix.contraAtaque
                    }
                    println("Harry acierta el hechizo y Bellatrix huye")
                    mazmorra[harry.fila][harry.columna] = harry
                    colocarPosAleatoria(Voldemort(), mazmorra)
                }while (random > 60)
            }
            dementor ->{
                do{
                    val random = (0..100).random()
                    if(random > 60){
                        println("Harry ha fallado")
                        dementor.contraAtaque()
                        harry.vida -= dementor.contraAtaque
                    }
                    println("Harry acierta el hechizo y ejecuta al dementor")
                    mazmorra[harry.fila][harry.columna] = harry
                    colocarPosAleatoria(Voldemort(), mazmorra)
                }while (random > 60)
            }
        }
    }

    private fun movimiento(){
        println("¿Harry hacia donde quieres ir?")
        println(
            "N" +
            "\nS" +
            "\nW" +
            "\nE"
        )
        val direccion = readln().trim().uppercase()
        if(direccion != "N" && direccion != "S" && direccion != "W" && direccion != "E"){
            println("Solo puedes moverte en las direcciones N, S, W, E.")
        }
        if(mazmorra[harry.fila][harry.columna] == harry){
            mazmorra[harry.fila][harry.columna] = null
        }
        when(direccion){
            "N" -> mazmorra[harry.fila - 1][harry.columna] = harry
            "S" -> mazmorra[harry.fila +1][harry.columna] = harry
            "W" -> mazmorra[harry.fila][harry.columna -1] = harry
            "E" -> mazmorra[harry.fila][harry.columna +1] = harry
        }
    }

    private fun inicializarMazmorra(mazmorra: Array<Array<Any?>>): Array<Array<Any?>> {
        repeat(numHolo){
            colocarPosAleatoria(Horrocurxes(), mazmorra)
        }
        repeat(numDementors){
            colocarPosAleatoria(Dementor(), mazmorra)
        }
        colocarPosAleatoria(Voldemort(), mazmorra)
        colocarPosAleatoria(Bellatrix(), mazmorra)
        colocarPosAleatoria(McGonall(), mazmorra)
        colocarPosAleatoria(Hermione(), mazmorra)
        colocarPosAleatoria(Ron(), mazmorra)
        return mazmorra
    }

    private fun colocarPosAleatoria(item: Any, mazmorra: Array<Array<Any?>>): Array<Array<Any?>>{
        var fila: Int
        var columna: Int
        do{
            fila = (0..mazmorra.size -1).random()
            columna = (0..mazmorra.size -1).random()
        }while (mazmorra[fila][columna] != null)
        mazmorra[fila][columna] = item
        return mazmorra
    }

    private fun printMazmorra(mazmorra: Array<Array<Any?>>){
        for(i in mazmorra.indices){
            for(j in mazmorra[i].indices){
                when(mazmorra[i][j]){
                    is Harry -> print("[ \uD83E\uDD13 ]")
                    is Horrocurxes -> print("[ \uD83D\uDD2E ]")
                    is Dementor -> print("[ \uD83D\uDC7B ]")
                    is Voldemort -> print("[ \uD83D\uDC7D ]")
                    is Bellatrix -> print("[ \uD83D\uDC80 ]")
                    is McGonall -> print("[ \uD83E\uDDD9 ]")
                    is Hermione -> print("[ \uD83E\uDDD9\u200D♀\uFE0F ]")
                    is Ron -> print("[ \uD83D\uDC68\u200D\uD83E\uDDB0]")
                    else -> print("[     ]")
                }
            }
            println()
        }
        println()
    }

}
