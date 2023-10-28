abstract class Llamada(
    var numOrigen: String,
    var numDestino: String,
    var duracion: Int
) {
    abstract var coste: Int

    fun getNumOrigen(): String {
        return this.numOrigen
    }

    fun getNumDestino(): String {
        return this.numDestino
    }

    fun getDuracion(): Int {
        return this.duracion
    }

    fun getCoste(): Int {

        return this.coste * this.duracion
    }
}