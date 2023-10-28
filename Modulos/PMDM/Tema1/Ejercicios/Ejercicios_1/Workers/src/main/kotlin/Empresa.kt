fun main() {
    var companyEmployees: ArrayList<Trabajador> = ArrayList()
    do {
        showMenu()
        var userSelection: Int = getUserSelection()
        executeOption(userSelection, companyEmployees)

    } while (userSelection != 0)
    println("System exiting...")
}

fun executeOption(userSelection: Int, companyEmployees: ArrayList<Trabajador>) {

    when (userSelection) {
        1 -> companyEmployees.add(addNewWorker(companyEmployees))
        2 -> listWorkers(companyEmployees)
        3 -> listSpecificWorker(companyEmployees)
    }
}

fun listSpecificWorker(companyEmployees: ArrayList<Trabajador>) {
    print("Specify the worker DNI: ")
    var dniToFind = readln()
    for(item in companyEmployees){
        if(dniToFind == item.getDNI()){
            item.mostrarDatos()
        }
    }
}

fun listWorkers(companyEmployees: ArrayList<Trabajador>) {
    var typeOfWorker: Int = askTypeOfWorker()
    when (typeOfWorker) {
        1 -> companyEmployees.filterIsInstance<Jefe>().forEach { employee: Jefe -> employee.mostrarDatos() }
        2 -> companyEmployees.filterIsInstance<Asalariado>().forEach { employee: Asalariado -> employee.mostrarDatos() }
        3 -> companyEmployees.filterIsInstance<Autonomo>().forEach { employee: Autonomo -> employee.mostrarDatos() }
    }


}

fun addNewWorker(companyEmployees: ArrayList<Trabajador>): Trabajador {
    var typeOfWorker: Int
    do {
        typeOfWorker = askTypeOfWorker()
        if (companyEmployees.any { it is Jefe } && typeOfWorker == 1) {
            println("The company has already a boss. Choose another option.")
        }
    } while (companyEmployees.any { it is Jefe } && typeOfWorker == 1)
    return newWorkerData(typeOfWorker)

}

fun newWorkerData(typeOfWorker: Int): Trabajador {
    var employee: Trabajador
    println("Name: ")
    var name: String = readln()
    println("Surname: ")
    var surname: String = readln()
    println("DNI: ")
    var dni: String = readln()
    when (typeOfWorker) {
        1 -> {
            println("Stocks: ")
            var stocks: Int = readln().toInt()
            println("Benefits: ")
            var benefit: Double = readln().toDouble()
            employee = Jefe(name, surname, dni, stocks, benefit)
        }

        2 -> {
            println("Salary: ")
            var salary: Double = readln().toDouble()
            println("Annual payments: ")
            var numberOfPayments: Int = readln().toInt()
            employee = Asalariado(name, surname, dni, salary, numberOfPayments, true)
        }

        else -> {
            println("Annual salary: ")
            var salary: Double = readln().toDouble()
            employee = Autonomo(name, surname, dni, salary, true)
        }

    }
    return employee
}

fun askTypeOfWorker(): Int {
    println("Which type of worker would you like to introduce: ")
    println("1.- Boss.")
    println("2.- Employee.")
    println("3.- Self-Employee.")
    return readln().toInt()
}

fun getUserSelection(): Int {
    print("Your choice: ")
    return readln().toInt()
}

fun showMenu() {
    println("COMPUGLOBALHIPERMEGANET Management Systems.")
    println("Choose one of the options from below.")
    println("1.- Add a new worker.")
    println("2.- List workers.")
    println("3.- Show worker info.")
    println("0.- Exit.")
}
