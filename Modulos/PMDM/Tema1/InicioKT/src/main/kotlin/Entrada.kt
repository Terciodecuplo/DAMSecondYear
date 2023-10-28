class Entrada {
    var nombre = "Borja"
    var edad: Int= 34
    var correo: String? = null
    val DNI = "123123A"

    fun main(){
        par(2)
        println(division(5,3))
        println(divisionDos())
    }
    fun par(x: Int): Unit{
       if( x%2==0) {
           println("Número evaluado ${x*2}")
       }
    }

    fun division(x: Int, y:Int):Double{
        return x.toDouble()/y as Double
    }

    fun divisionDos():Double{
        println("Introduce el primer operando:")
        var x:Int = readln().toInt()
        println("Introduce el segundo operando:")
        var y:Int = readLine()?.toInt() ?:0
        return x.toDouble()/y
    }
}