class Asalariado(
    nombre: String,
    apellido: String,
    dni: String,
    private var sueldo: Double,
    private var pagas: Int,
    private var contratado: Boolean
) : Trabajador(nombre, apellido, dni) {

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Sueldo Mensual: $sueldo")
        println("Sueldo Anual: ${sueldo*pagas}")
        println("Nº Pagas: $pagas")
    }

    fun getSueldo(): Double {
        return this.sueldo
    }

    fun getPagas(): Int {
        return this.pagas
    }

    fun getContratado(): Boolean {
        return this.contratado
    }
}