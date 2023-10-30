import kotlin.math.cos
import kotlin.random.Random

class Centralita {

    private val callRegister = ArrayList<Llamada>()

    fun registrarLlamadas(numOrigen: String, numDestino: String, duration: Int): ArrayList<Llamada> {
        var tipoLlamada: Int = Random.nextInt(1, 3)
        var llamada: Llamada
        when (tipoLlamada) {
            1 -> llamada = LlamadaLocal(numOrigen, numDestino, duration)
            2 -> llamada = LlamadaProvincial(numOrigen, numDestino, duration)
            else -> llamada = LlamadaNacional(numOrigen, numDestino, duration)
        }
        callRegister.add(llamada)
        mostrarRegistro()
        return callRegister
    }

    fun mostrarRegistro() {
        for(item in callRegister){
            println("Número Origen: ${item.numOrigen} " +
                    "; Número Destino: ${item.numDestino} " +
                    "; Duración: ${item.duracion} " +
                    "; Coste Total: ${item.getCosteLlamada()/100}€.")
        }
    }

    fun costeTotal(){
        var costeTotal: Double = 0.0
        for(item in callRegister){
            costeTotal += item.getCosteLlamada()
        }
        println("The total cost of the calls is: ${costeTotal/100}€")
    }
}