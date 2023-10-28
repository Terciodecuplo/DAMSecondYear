package model

open class Usuario(var nombre: String, var apellido: String, var dni: String) {


    // atributos nombre(o) apellido(o) DNI(o) correo(?)

    var correo: String? = null

    //constructores
    //primario --> base
    //secundario --> base + adicional

    constructor(nombre: String, apellido: String, dni: String, correo:String) : this(nombre, apellido, dni){
        this.correo = correo
    }

}