class LlamadaProvincial(
    numOrigen: String,
    numDestino: String,
    duracion: Int
) : Llamada(numOrigen, numDestino, duracion) {
    override var coste: Int = 15

    override fun getCosteLlamada(): Int {
        return coste * duracion
    }
}