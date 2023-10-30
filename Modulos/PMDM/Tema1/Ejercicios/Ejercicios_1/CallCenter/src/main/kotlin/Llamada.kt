abstract class Llamada(
    val numOrigen: String,
    val numDestino: String,
    val duracion: Int
) {
    abstract var coste: Int

    abstract fun getCosteLlamada(): Int
}