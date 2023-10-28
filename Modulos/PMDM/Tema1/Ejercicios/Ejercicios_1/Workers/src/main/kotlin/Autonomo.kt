class Autonomo(
    nombre: String,
    apellido: String,
    dni: String,
    private var sueldo: Double,
    private var contratado: Boolean
) : Trabajador(nombre, apellido, dni) {

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Sueldo Anual: $sueldo")
    }

    fun getSueldo() : Double{
        return this.sueldo
    }
    fun getContratado() : Boolean{
        return this.contratado
    }
}