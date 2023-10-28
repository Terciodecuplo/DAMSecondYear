class Jefe(
    nombre: String,
    apellido: String,
    dni: String,
    private var acciones: Int,
    private var beneficio: Double
) : Trabajador(nombre, apellido, dni) {

   //var bossData:(String) -> Unit = { println("Nombre: $nombre Apellidos: $apellido DNI: $dni Acciones: $acciones Beneficios: $beneficio")}

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