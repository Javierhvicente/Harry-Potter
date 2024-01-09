class Mazmorra (val mapSize: Int = 6, val numHolo: Int = 7, val numDementors: Int = 6) {
    private var mazmorra = Array(mapSize) { arrayOfNulls<Any>(mapSize) }
    val harry = Harry()
    private val horrocurxes = Horrocurxes()
    private val dementor = Dementor()
    private val voldemort = Voldemort()
    private val bellatrix = Bellatrix()
    private val mcGonall = McGonall()
    private val hermione = Hermione()
    private val ron = Ron()
    private var horrDestruidos = 0
    private var dementoresEjecutados = 0
    private var marked = false

    init {
        println("Bienvenido Harry")
        mazmorra[harry.fila][harry.columna] = Harry()
        mazmorra = inicializarMazmorra(mazmorra)
    }

    private fun isMarked(): Boolean {
        if(!marked){
            return false
        }
        return true
    }

    fun aventura() {
        do {
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
            is Voldemort -> {
                println("Harry se ha encontrado a Voldemort")
                do {
                    val random = (0..100).random()
                    if (random > 60) {
                        println("Harry ha fallado")
                        voldemort.contraAtaque()
                        harry.vida -= voldemort.contraAtaque
                    }else{
                        println("Harry acierta el hechizo y voldemort huye")
                        mazmorra[fila][columna] = Harry()
                        colocarPosAleatoria(Voldemort(), mazmorra)
                    }
                } while (random > 60 && harry.vida > 0)
            }

            is Bellatrix -> {
                println("Harry se ha encontrado a Bellatrix")
                do {
                    val random = (0..100).random()
                    if (random > 60) {
                        println("Harry ha fallado")
                        bellatrix.contraAtaque()
                        harry.vida -= bellatrix.contraAtaque
                    }else{
                        println("Harry acierta el hechizo y Bellatrix huye")
                        mazmorra[fila][columna] = Harry()
                        colocarPosAleatoria(Bellatrix(), mazmorra)
                    }

                } while (random > 60 && harry.vida > 0)
            }

            is Dementor -> {
                do {
                    val random = (0..100).random()
                    if (random > 60) {
                        println("Harry ha fallado")
                        dementor.contraAtaque()
                        harry.vida -= dementor.contraAtaque
                    }else{
                        println("Harry acierta el hechizo y ejecuta al dementor")
                        dementoresEjecutados++
                        mazmorra[fila][columna] = Harry()
                    }

                } while (random > 60 && harry.vida > 0)
            }

            is Horrocurxes -> {
                println("Harry ha destruido un horrocrux")
                horrDestruidos++
                mazmorra[fila][columna] = Harry()
            }

            is McGonall -> {
                if (harry.vida >= 100) {
                    println("Harry ya tienes toda la vida")
                    mazmorra[fila][columna] = McGonall()
                    mcGonall.cambiarVista(mcGonall.vista)
                } else {
                    println("McGonall cura las heridas de Harry con un hechizo")
                    harry.vida += mcGonall.curacion
                    mazmorra[fila][columna] = Harry()
                }
            }

            is Hermione -> {
                if (harry.vida >= 100) {
                    println("Harry ya tienes toda la vida lokete")
                    mazmorra[fila][columna] = Hermione()
                    hermione.cambiarVista(hermione.vista)
                } else {
                    println("Hermione cura las heridas de Harry con un hechizo")
                    harry.vida += hermione.curacion
                    mazmorra[fila][columna] = Harry()
                }
            }

            is Ron -> {
                if (harry.vida >= 100) {
                    println("Harry que tienes toda la vida calla ya ")
                    mazmorra[fila][columna] = Ron()
                    ron.cambiarVista(ron.vista)
                    isMarked()
                } else {
                    if ((0..100).random() < 30) {
                        println("Ron ha metido la pata y ha fallado la curación haciendo daño a harry")
                        harry.vida -= 10
                    } else {
                        println("Ron cura las heridas de Harry con un hechizo")
                        harry.vida += ron.curacion
                        mazmorra[fila][columna] = Harry()
                    }
                }
            }
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
                    true -> print("[   ]")
                    else -> print("[ ? ]")
                }
            }
            println()
        }
        println()
    }
}



