import kotlin.random.Random

fun main() {

    val centralita = Centralita()
    do {
        println("What do you want to do:")
        println("1.- Register a call.")
        println("2.- Show total costs.")
        println("3.- Show calls.")
        println("0.- Exit.")
        print("Your choice")
        var userChoice = readln().toInt()
        when (userChoice) {
            1-> {
                var numOrigen
                        : String = Random.nextInt(600000000, 799999999).toString()
                var numDestino
                        : String = Random.nextInt(600000000, 799999999).toString()
                var duration
                        : Int = Random.nextInt(30, 600)
                centralita.registrarLlamadas(numOrigen, numDestino, duration)
            }
            2-> centralita.costeTotal()
            3-> centralita.mostrarRegistro()
        }
    }while(userChoice != 0)
}
