import java.awt.Cursor
import kotlin.math.E

class Crupier(var nombre:String, var vidasCru:Int){

    fun disparar(escopeta: Escopeta, jugador: Jugador) {


        if (escopeta.numeroBalasCargador == escopeta.balasVacias){ //No dispara
            println("La bala ha fallado")
        }else if (escopeta.numeroBalasCargador == escopeta.balasCargadas){
            println("La bala ha impactado")
            jugador.vidasPlayer--
            println("Vida del jugador -1")
        }
    }
    fun autoDisparar(crupier: Crupier, escopeta: Escopeta){
        if (escopeta.numeroBalasCargador == escopeta.balasVacias){ //no dispara
            println("La bala ha fallado")
        }else if (escopeta.numeroBalasCargador == escopeta.balasCargadas){
            println("La bala ha impactado")
            crupier.vidasCru--
            println("Vida del crupier -1")
        }
    }

}


class Escopeta(jugador: Jugador, crupier: Crupier){
    var numeroBalasCargador = (3..8).random()
    var balasCargadas = (1..numeroBalasCargador).random() //poner un numero aleatorio de las balas cargadas para que sean las balas cargadas es decir las que disparan
    var balasVacias = numeroBalasCargador - balasCargadas//seria el resto de las balas que quedan

    init {
        println("La escopeta tiene en el cargador $numeroBalasCargador")
        println("Cargadas hay $balasCargadas")
        println("Falsas hay $balasVacias")
    }

}



class Jugador(var nombre: String, var vidasPlayer: Int) {
    fun disparar(crupier: Crupier, escopeta: Escopeta) {

        escopeta.numeroBalasCargador
        if (escopeta.numeroBalasCargador == escopeta.balasVacias){
            println("La bala ha fallado")
        }else{
            println("La bala ha impactado")
            crupier.vidasCru--
            println("Vida del crupier -1")
        }
    }

    fun autoDisparar(jugador: Jugador, escopeta: Escopeta){
        escopeta.numeroBalasCargador
        if (escopeta.numeroBalasCargador == escopeta.balasVacias){
            println("La bala ha fallado")
        }else{
            println("La bala ha impactado")
            jugador.vidasPlayer--
            println("Vida del jugador -1")
        }
    }
    fun datosJugador(jugador: Jugador){
        println("Los datos del Jugador son los siguientes:")
        println("A ${jugador.nombre} le quedan ${jugador.vidasPlayer} vidas.")
    }
}
fun main() {

    var crupier = Crupier("Crupier", 2)
    var jugador = Jugador("Elia", 2)
    var escopeta = Escopeta(jugador, crupier)


    var juegoOk = true

    while (juegoOk){
        turnoCrupier(crupier, jugador, escopeta)
        finJuego(jugador, crupier)
        turnoJugador(jugador, crupier, escopeta)
    }


}
fun turnoCrupier(crupier: Crupier, jugador: Jugador, escopeta: Escopeta){
    print("TURNO CRUPIER. \t \t \t VIDAS -> ${crupier.vidasCru}")
    println()
    println("Que deseas hacer:")
    println("(1)Disparar al contrincante. (2) Dispararte a ti mismo")
    try {
        var opcion= readln().toInt()

        when (opcion) {
            1 -> crupier.disparar(escopeta, jugador) //El IDE me pide to eso pero no entiendo porque
            2 -> crupier.autoDisparar(crupier, escopeta)
            else -> println("Pierdes turno por gracioso")
        }
    } catch (e: NumberFormatException){
        println("Esto es serio, por gracioso pierdes el turno.")
    }
}



fun turnoJugador(jugador: Jugador, crupier: Crupier, escopeta: Escopeta){
    println("TURNO d ${jugador.nombre}  \t \t \t VIDAS -> ${jugador.vidasPlayer}")
    println()
    println("Que deseas hacer:")
    print("(1)Disparar al contrincante. (2) Dispararte a ti mismo")
    try {
        var opcion= readln().toInt()

        when (opcion) {
            1 -> jugador.disparar(crupier, escopeta)
            2 -> jugador.autoDisparar(jugador, escopeta)
            else -> println("Pierdes el turno por gracioso")
        }
    } catch (e: NumberFormatException){
        println("Esto es serio, por gracioso pierdes el turno.")
    }
}





fun finJuego(jugador: Jugador, crupier: Crupier): Boolean {
    println()
    if (jugador.vidasPlayer <= 0 ){
        println("Mala suerte...")
        return false
    }else if (crupier.vidasCru <= 0){
        println("Has tenido suerte, quizas en la próxima no tengas tanta suerte")
        return false
    }else{
        println("QUE SIGA LA LUCHA POR SOBREVIVIR!!")
        println()
        return true
    }
}


fun informacionPrincipal(escopeta: Escopeta){
    println("Hay ${escopeta.numeroBalasCargador} balas en el cargador")
    println("de las ${escopeta.numeroBalasCargador} hay ${escopeta.balasCargadas} balas que matan y ${escopeta.balasVacias} balas que no matan.")
}

