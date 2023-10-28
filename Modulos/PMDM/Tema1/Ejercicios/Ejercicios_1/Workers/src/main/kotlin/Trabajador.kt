abstract class Trabajador(private var nombre: String, private var apellido: String, private var dni: String) {

    open fun mostrarDatos(){
        println("Nombre: $nombre")
        println("Apellidos: $apellido")
        println("DNI: $dni")
    }
    fun getNombre() : String{
        return this.nombre
    }
    fun getApellido() : String{
        return this.apellido
    }
    fun getDNI() : String{
        return this.dni
    }
}