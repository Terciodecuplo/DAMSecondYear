class LlamadaLocal(
    numOrigen: String,
    numDestino: String,
    duracion: Int
) : Llamada(numOrigen, numDestino, duracion) {
    override var coste: Int = 0

    override fun getCosteLlamada(): Int {
        return coste * duracion
    }
}