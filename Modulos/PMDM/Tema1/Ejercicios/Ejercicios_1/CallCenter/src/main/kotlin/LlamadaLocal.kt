class LlamadaLocal(
    numOrigen: String,
    numDestino: String,
    duracion: Int,
    override var coste: Int = 0
) : Llamada(numOrigen, numDestino, duracion) {


}