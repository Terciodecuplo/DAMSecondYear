class Jefe(
    nombre: String,
    apellido: String,
    dni: String,
    private var acciones: Int,
    private var beneficio: Double
) : Trabajador(nombre, apellido, dni) {

    override fun mostrarDatos(){
        super.mostrarDatos()
        println("Nº Acciones: $acciones")
        println("Beneficios: $beneficio")
    }

    fun getAcciones(): Int {
        return this.acciones
    }

    fun getBeneficio(): Double {
        return this.beneficio
    }
}