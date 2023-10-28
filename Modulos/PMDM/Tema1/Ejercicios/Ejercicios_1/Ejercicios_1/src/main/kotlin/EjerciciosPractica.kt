import kotlin.random.Random

fun main() {


    do {
        println("Please, choose an option from below (0 to exit).")
        println("1.- Greater than.")
        println("2.- Multiple of 20.")
        println("3.- 1, 2, fizzbuzz!!")
        println("4.- Randomizer.")
        println("0.- Exit.")
        print("Your choose: ")
        val optionMenu = readln().toInt()
        when (optionMenu) {
            1 -> greaterThan()
            2 -> multiple()
            3 -> numberSubstitution()
            4 -> randomizer()
        }
    } while (optionMenu != 0)

}

fun greaterThan() {

    print("Choose one number: ")
    val firstNumber = readln()
    print("Choose another number: ")
    val secondNumber = readln()
    if (firstNumber > secondNumber) {
        println("$firstNumber is greater than $secondNumber.")
    } else if (firstNumber == secondNumber) {
        println("$firstNumber is equals than $secondNumber.")
    } else {
        println("$secondNumber is greater than $firstNumber.")

    }

}

fun multiple() {

    print("Please, choose an Integer number:")
    val numberChoosen = readln().toInt()
    if (numberChoosen % 20 == 0) {
        print("$numberChoosen is a multiple of 20 ")
    } else {
        print("$numberChoosen is not a multiple of 20 ")
    }
    if (numberChoosen <= 100 && numberChoosen >= -100) {
        println("and is between 100 and -100.")
    } else {
        println("and is not between 100 and -100.")
    }
}

fun numberSubstitution() {

    for (i in 1..100) {
        if (i % 3 == 0 && i % 5 == 0) {
            println("fizzbuzz")
        } else if (i % 3 == 0) {
            println("fizz")
        } else if (i % 5 == 0) {
            println("buzz")
        } else {
            println(i)
        }
    }
}

fun randomizer() {
    do {
        var randomNumber = Random.nextInt(0, 2)
        println("I've generated a random number between 0 to 20. Choose a number and let's see if you win!!")
        do {
            var numberChoosed = readln().toInt()
            if (numberChoosed == randomNumber) {
                println("Yay!!! You win!!!")
            } else {

                var randomResponse = Random.nextInt(1, 5)
                when (randomResponse) {
                    1 -> println("Nope, sorry. Try again")
                    2 -> println("Haha, not even close. Try again")
                    3 -> println("You win... a defeat. Try again")
                    4 -> println("You mad bro?. Try again")
                    5 -> println("Ok, at least it's a number. Try again")
                }
            }
        } while (numberChoosed != randomNumber)
        print("Do you want to try again? S/N ")
        var tryAgain = readln().take(1).uppercase()
    } while (tryAgain == "S")

}