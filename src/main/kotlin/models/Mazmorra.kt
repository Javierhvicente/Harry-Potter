package models

class Mazmorra (val mapSize: Int = 6, val numHolo: Int = 7, val numDementors: Int = 6, var horrDestruidos: Int = 0, var dementoresEjecutados:Int = 0) {
    private var mazmorra = Array(mapSize) { arrayOfNulls<Any>(mapSize) }
    private val harry = Harry()
    private val horrocurxes = Horrocurxes()
    private val dementor = Dementor()
    private val voldemort = Voldemort()
    private val bellatrix = Bellatrix()
    private val mcGonall = McGonall()
    private val hermione = Hermione()
    private val ron = Ron()


    init {
        println("Bienvenido Harry")
        mazmorra[harry.fila][harry.columna] = Harry()
        mazmorra = inicializarMazmorra(mazmorra)
    }

    fun aventura() {
        do {
            printMazmorra(mazmorra)
            printCamino(mazmorra)
            juego()
            println("$horrDestruidos Horrocruxes destruidos")
            println("${harry.vida} puntos de vida")
            println("Dementores ejecutados: $dementoresEjecutados")
        } while (horrDestruidos < 7 && harry.vida > 0)
        if (harry.vida <= 0) {
            println("Harry Potter ha muerto en combate")
        }
        println("Harry ha conseguido salir de la mazmorra con vida y ha cunplido su misión de acabar con los siete Horrocruxes")

    }

    private fun accion(fila: Int, columna: Int) {
        when (mazmorra[fila][columna]) {
            is Voldemort -> voldemort.interacción(mazmorra, fila, columna)
            is Bellatrix -> bellatrix.interacción(mazmorra, fila, columna)
            is Dementor -> dementor.interaccion(Mazmorra(),mazmorra, fila, columna)
            is Horrocurxes -> horrocurxes.interracción(Mazmorra(), mazmorra, fila, columna)
            is McGonall -> mcGonall.interacción(mazmorra, fila, columna)
            is Hermione -> hermione.interacción(mazmorra, fila, columna)
            is Ron -> ron.interacción(mazmorra,fila, columna)
            true -> mazmorra[fila][columna] = Harry()
            null -> mazmorra[fila][columna] = Harry()
        }
    }

    private fun juego() {
        var nuevaFila = harry.fila
        var nuevaCol = harry.columna
        println("¿Harry hacia donde quieres ir?")
        println(
            "N" +
                    "\nS" +
                    "\nW" +
                    "\nE"
        )
        val direccion = readln().trim().uppercase()
        if (direccion != "N" && direccion != "S" && direccion != "W" && direccion != "E") {
            println("Solo puedes moverte en las direcciones N, S, W, E.")
        } else {
            if (mazmorra[harry.fila][harry.columna] is Harry) {
                mazmorra[harry.fila][harry.columna] = true
            }
            when (direccion) {
                "N" -> if (harry.fila > 0) {
                    nuevaFila--
                }

                "S" -> if (harry.fila < mazmorra.size - 1) {
                    nuevaFila++
                }

                "W" -> if (harry.columna > 0) {
                    nuevaCol--
                }

                "E" -> if (harry.columna < mazmorra.size - 1) {
                    nuevaCol++
                }
            }
        }
        harry.fila = nuevaFila
        harry.columna = nuevaCol
        accion(harry.fila, harry.columna)


    }

    private fun inicializarMazmorra(mazmorra: Array<Array<Any?>>): Array<Array<Any?>> {
        repeat(numHolo) {
            colocarPosAleatoria(Horrocurxes(), mazmorra)
        }
        repeat(numDementors) {
            colocarPosAleatoria(Dementor(), mazmorra)
        }
        colocarPosAleatoria(Voldemort(), mazmorra)
        colocarPosAleatoria(Bellatrix(), mazmorra)
        colocarPosAleatoria(McGonall(), mazmorra)
        colocarPosAleatoria(Hermione(), mazmorra)
        colocarPosAleatoria(Ron(), mazmorra)
        return mazmorra
    }

    private fun colocarPosAleatoria(item: Any, mazmorra: Array<Array<Any?>>): Array<Array<Any?>> {
        var fila: Int
        var columna: Int
        do {
            fila = (0..mazmorra.size - 1).random()
            columna = (0..mazmorra.size - 1).random()
        } while (mazmorra[fila][columna] != null)
        mazmorra[fila][columna] = item
        return mazmorra
    }


    private fun printMazmorra(mazmorra: Array<Array<Any?>>) {
        for (i in mazmorra.indices) {
            for (j in mazmorra[i].indices) {
                when (mazmorra[i][j]) {
                    is Harry -> print("[ \uD83E\uDD13 ]")
                    is Horrocurxes -> print("[ \uD83D\uDD2E ]")
                    is Dementor -> print("[ \uD83D\uDC7B ]")
                    is Voldemort -> print("[ \uD83D\uDC7D ]")
                    is Bellatrix -> print("[ \uD83D\uDC80 ]")
                    is McGonall -> print("[ \uD83E\uDDD9 ]")
                    is Hermione -> print("[ \uD83E\uDDD9\u200D♀\uFE0F ]")
                    is Ron -> print("[ \uD83D\uDC68\u200D\uD83E\uDDB0 ]")
                    else -> print("[     ]")
                }
            }
            println()
        }
        println()
    }

    private fun printCamino(mazmorra: Array<Array<Any?>>) {
        for (i in mazmorra.indices) {
            for (j in mazmorra[i].indices) {
                when (mazmorra[i][j]) {
                    is Harry -> print("[ \uD83E\uDD13 ]")
                    is McGonall ->{
                        if(!mcGonall.vista){
                            print("[ ? ]")
                        }else{
                            print("[ \uD83E\uDDD9 ]")

                        }
                    }
                    is Hermione ->{
                        if(!hermione.vista){
                            print("[ ? ]")
                        }else{
                            print("[ \uD83E\uDDD9\u200D♀\uFE0F ]")

                        }
                    }
                    is Ron ->{
                        if(!ron.vista){
                            print("[ ? ]")
                        }else{
                            print("[ \uD83D\uDC68\u200D\uD83E\uDDB0 ]")

                        }
                    }
                    is Voldemort ->{
                        if(!voldemort.vista){
                            print("[ ? ]")
                        }else{
                            print("[ \uD83D\uDC7D ]")
                        }
                    }
                    is Bellatrix ->{
                        if(!bellatrix.vista){
                            print("[ ? ]")
                        }else{
                            print("[ \uD83D\uDC7D ]")
                        }
                    }
                    true -> print("[   ]")
                    else -> print("[ ? ]")
                }
            }
            println()
        }
        println()
    }
}



