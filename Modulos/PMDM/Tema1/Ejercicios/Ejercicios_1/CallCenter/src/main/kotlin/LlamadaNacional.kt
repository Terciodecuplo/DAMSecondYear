import kotlin.random.Random

class LlamadaNacional(
    numOrigen: String,
    numDestino: String,
    duracion: Int,
    val franja: Int = Random.nextInt(0, 2)
) : Llamada(numOrigen, numDestino, duracion) {

    override var coste: Int = 20 + 5 * franja


    override fun getCosteLlamada(): Int {
        return coste * duracion
    }
}