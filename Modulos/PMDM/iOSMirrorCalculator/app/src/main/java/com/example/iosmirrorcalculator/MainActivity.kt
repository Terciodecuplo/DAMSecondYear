package com.example.iosmirrorcalculator

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.iosmirrorcalculator.databinding.ActivityMainBinding
import org.apache.commons.math3.special.Gamma
import kotlin.math.cbrt
import kotlin.math.cos
import kotlin.math.cosh
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sinh
import kotlin.math.sqrt
import kotlin.math.tan
import kotlin.math.tanh
import kotlin.random.Random

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var firstNumberToOperate = 0.0
    private var secondNumberToOperate = 0.0
    private var secondNumberToOperateStored = 0.0
    private var acPressed = false
    private var equalsPressed = false
    private var memoryPressed = false
    private var radiansMode = false
    private var operationSelected = ""
    private var lastOperationSelected = ""
    private var dividedByZero = false
    private var memo = "0"
    private val operationButtonsIds = setOf(
        R.id.sumBtn, R.id.sustractionBtn, R.id.multiplyBtn, R.id.divisionBtn
    )
    private val numericButtonsIds = setOf(
        R.id.oneNumberBtn,
        R.id.twoNumberBtn,
        R.id.threeNumberBtn,
        R.id.fourNumberBtn,
        R.id.fiveNumberBtn,
        R.id.sixNumberBtn,
        R.id.sevenNumberBtn,
        R.id.eightNumberBtn,
        R.id.nineNumberBtn,
        R.id.zeroNumberBtn,
        R.id.piBtn,
        R.id.eBtn
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val allButtonsBinds = setOf(
            binding.acBtn,
            binding.zeroNumberBtn,
            binding.oneNumberBtn,
            binding.twoNumberBtn,
            binding.threeNumberBtn,
            binding.fourNumberBtn,
            binding.fiveNumberBtn,
            binding.sixNumberBtn,
            binding.sevenNumberBtn,
            binding.eightNumberBtn,
            binding.nineNumberBtn,
            binding.signBtn,
            binding.percentBtn,
            binding.decimalBtn,
            binding.equalBtn,
            binding.sumBtn,
            binding.sustractionBtn,
            binding.multiplyBtn,
            binding.divisionBtn,
            binding.eBtn,
            binding.randomBtn,
            binding.deleteBtn,
            binding.memoryBtn,
            binding.factorialBtn,
            binding.squaredBtn,
            binding.cubedBtn,
            binding.powerOfYBtn,
            binding.tenToTheXBtn,
            binding.sqrtBtn,
            binding.cbrtBtn,
            binding.naeperianBtn,
            binding.logarithmBtn,
            binding.sinBtn,
            binding.cosBtn,
            binding.tanBtn,
            binding.piBtn,
            binding.sinhBtn,
            binding.coshBtn,
            binding.tanhBtn,
            binding.radBtn
        )

        binding.acBtn.text = savedInstanceState?.getCharSequence("acButtonState") ?: "AC"
        binding.resultText.text = savedInstanceState?.getCharSequence("result") ?: "0"

        allButtonsBinds.forEach { button -> button?.setOnClickListener(this) }

        binding.memoryBtn?.setOnLongClickListener {
            memo = binding.resultText.text.toString()
            if (memo != "0") {
                Toast.makeText(this, "Number stored!", Toast.LENGTH_LONG).show()
                binding.memoryBtn?.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.light_grey
                    )
                )
                binding.memoryBtn?.setTextColor(ContextCompat.getColor(this, R.color.black))
            }
            if (memo == "0") {
                Toast.makeText(this, "Memory reset!", Toast.LENGTH_LONG).show()
                binding.memoryBtn?.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.dark_grey
                    )
                )
                binding.memoryBtn?.setTextColor(ContextCompat.getColor(this, R.color.white))
            }
            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putCharSequence("acButtonState", binding.acBtn.text)
        outState.putCharSequence("result", binding.resultText.text)
        outState.putString("operationSelected", operationSelected)
        outState.putString("lastOperationSelected", lastOperationSelected)
        outState.putDouble("firstNumberToOperate", firstNumberToOperate)
        outState.putDouble("secondNumberToOperate", secondNumberToOperate)
    }

    override fun onClick(buttonPressed: View?) {
        resetOperationSelectedColours()

        when (buttonPressed?.id) {

            binding.acBtn.id -> {
                if (binding.acBtn.text.toString() == "AC") {
                    acPressed = true
                    dividedByZero = false
                    firstNumberToOperate = 0.0
                    secondNumberToOperate = 0.0
                    secondNumberToOperateStored = 0.0
                    lastOperationSelected = ""
                    memo = "0"
                    operationSelected = ""
                    lastOperationSelected = ""
                    binding.resultText.text = "0"
                    binding.historyText.text = ""
                    binding.memoryBtn?.setBackgroundColor(
                        ContextCompat.getColor(
                            this, R.color.dark_grey
                        )
                    )
                    binding.memoryBtn?.setTextColor(ContextCompat.getColor(this, R.color.white))
                } else {
                    dividedByZero = false
                    binding.resultText.text = "0"
                    if (operationSelected == "") {
                        firstNumberToOperate = 0.0
                        secondNumberToOperate = 0.0
                        secondNumberToOperateStored = 0.0
                    }
                    if (!equalsPressed) {
                        when (lastOperationSelected) {
                            "+" -> {
                                changeOperationSelectedColour(findViewById(binding.sumBtn.id))
                            }

                            "-" -> {
                                changeOperationSelectedColour(findViewById(binding.sustractionBtn.id))
                            }

                            "x" -> {
                                changeOperationSelectedColour(findViewById(binding.multiplyBtn.id))
                            }

                            "/" -> {
                                changeOperationSelectedColour(findViewById(binding.divisionBtn.id))
                            }

                            "xToTheY" -> {
                                binding.powerOfYBtn?.setBackgroundColor(
                                    ContextCompat.getColor(
                                        this, R.color.light_grey
                                    )
                                )
                                binding.powerOfYBtn?.setTextColor(
                                    ContextCompat.getColor(
                                        this, R.color.black
                                    )
                                )
                            }
                        }
                    }
                }
            }

            binding.oneNumberBtn.id -> {
                updateNumberSequence("1")
                //historyDisplayManager("1")
            }

            binding.twoNumberBtn.id -> {
                updateNumberSequence("2")
                //historyDisplayManager("2")
            }

            binding.threeNumberBtn.id -> {
                updateNumberSequence("3")
                //historyDisplayManager("3")
            }

            binding.fourNumberBtn.id -> {
                updateNumberSequence("4")
                //historyDisplayManager("4")
            }

            binding.fiveNumberBtn.id -> {
                updateNumberSequence("5")
                //historyDisplayManager("5")
            }

            binding.sixNumberBtn.id -> {
                updateNumberSequence("6")
                //historyDisplayManager("6")
            }

            binding.sevenNumberBtn.id -> {
                updateNumberSequence("7")
                // historyDisplayManager("7")
            }

            binding.eightNumberBtn.id -> {
                updateNumberSequence("8")
                //historyDisplayManager("8")
            }

            binding.nineNumberBtn.id -> {
                updateNumberSequence("9")
                //historyDisplayManager("9")
            }

            binding.zeroNumberBtn.id -> {
                updateNumberSequence("0")
                //historyDisplayManager("0")
            }

            binding.decimalBtn.id -> {
                if (!binding.resultText.text.toString().contains(".")) {
                    if (binding.resultText.text.toString() == "0") {
                        updateNumberSequence("0.")
                    } else {
                        updateNumberSequence(".")
                    }
                    //historyDisplayManager(".")
                }
            }

            binding.factorialBtn?.id -> {
                if (binding.resultText.text.toString().toDouble() < 0) {
                    dividedByZero = true
                }
                operationSelected = "!"
                operate()
            }

            binding.squaredBtn?.id -> {
                operationSelected = "squared"
                operate()
            }

            binding.cubedBtn?.id -> {
                operationSelected = "cubed"
                operate()
            }

            binding.powerOfYBtn?.id -> {
                operationSelected = "xToTheY"
                lastOperationSelected = "xToTheY"
                storeFirstOperator()
                binding.powerOfYBtn?.setBackgroundColor(
                    ContextCompat.getColor(
                        this, R.color.light_grey
                    )
                )
                binding.powerOfYBtn?.setTextColor(ContextCompat.getColor(this, R.color.black))
            }

            binding.tenToTheXBtn?.id -> {
                operationSelected = "tenToTheX"
                operate()
            }

            binding.sqrtBtn?.id -> {
                operationSelected = "sqrt"
                operate()
            }

            binding.cbrtBtn?.id -> {
                operationSelected = "cbrt"
                operate()
            }

            binding.naeperianBtn?.id -> {
                operationSelected = "n"
                operate()
            }

            binding.logarithmBtn?.id -> {
                operationSelected = "lg"
                operate()
            }

            binding.piBtn?.id -> {
                binding.resultText.text = Math.PI.toString()
            }

            binding.sinBtn?.id -> {
                operationSelected = "sin"
                operate()
            }

            binding.cosBtn?.id -> {
                operationSelected = "cos"
                operate()
            }

            binding.tanBtn?.id -> {
                operationSelected = "tan"
                operate()
            }

            binding.sinhBtn?.id -> {
                operationSelected = "sinh"
                operate()
            }

            binding.coshBtn?.id -> {
                operationSelected = "cosh"
                operate()
            }

            binding.tanhBtn?.id -> {
                operationSelected = "tanh"
                operate()
            }

            binding.radBtn?.id -> {
                radiansMode = !radiansMode
                if (radiansMode) {
                    binding.radBtn?.setBackgroundColor(
                        ContextCompat.getColor(
                            this, R.color.light_grey
                        )
                    )
                    binding.radBtn?.setTextColor(ContextCompat.getColor(this, R.color.black))
                } else {
                    binding.radBtn?.setBackgroundColor(
                        ContextCompat.getColor(
                            this, R.color.medium_grey
                        )
                    )
                    binding.radBtn?.setTextColor(ContextCompat.getColor(this, R.color.white))
                }
            }

            binding.randomBtn?.id -> {
                operationSelected = "rnd"
                operate()
            }

            binding.memoryBtn?.id -> {
                if (memo != "0") {
                    binding.resultText.text = memo
                    memoryPressed = true
                } else {
                    Toast.makeText(this, "Empty memory!", Toast.LENGTH_SHORT).show()
                }
            }

            binding.deleteBtn?.id -> {
                if (binding.resultText.text.toString() != "0") {
                    binding.resultText.text = binding.resultText.text.dropLast(1)
                    if (binding.resultText.text.toString() == "") {
                        binding.resultText.text = "0"
                    }
                }
            }

            binding.eBtn?.id -> {
                binding.resultText.text = Math.E.toString()
            }

            binding.sumBtn.id -> {/*if (binding.historyText.text.toString() == "") {
                    historyDisplayManager("${binding.resultText.text}+")
                } else {
                    historyDisplayManager("+")
                }*/
                equalsPressed = false
                if (lastOperationSelected != "") {
                    storeSecondOperator()
                    operate()
                    storeFirstOperator()
                }
                operationSelected = "+"
                changeOperationSelectedColour(findViewById(buttonPressed.id))
                storeFirstOperator()
                resetDisplay()
                lastOperationSelected = "+"
            }

            binding.sustractionBtn.id -> {/* if (binding.historyText.text.toString() == "") {
                     historyDisplayManager("${binding.resultText.text}-")
                 } else {
                     historyDisplayManager("-")
                 }*/
                equalsPressed = false
                if (lastOperationSelected != "") {
                    storeSecondOperator()
                    operate()
                    storeFirstOperator()
                }
                operationSelected = "-"
                changeOperationSelectedColour(findViewById(buttonPressed.id))
                storeFirstOperator()
                resetDisplay()
                lastOperationSelected = "-"
            }

            binding.multiplyBtn.id -> {/* if (binding.historyText.text.toString() == "") {
                     historyDisplayManager("${binding.resultText.text}x")
                 } else {
                     historyDisplayManager("x")
                 }*/
                equalsPressed = false
                if (lastOperationSelected != "") {
                    storeSecondOperator()
                    operate()
                    storeFirstOperator()
                }
                operationSelected = "x"
                changeOperationSelectedColour(findViewById(buttonPressed.id))
                storeFirstOperator()
                resetDisplay()
                lastOperationSelected = "x"
            }

            binding.divisionBtn.id -> {/*if (binding.historyText.text.toString() == "") {
                    historyDisplayManager("${binding.resultText.text}/")
                } else {
                    historyDisplayManager("/")
                }*/
                if (lastOperationSelected != "") {
                    storeSecondOperator()
                    operate()
                    storeFirstOperator()
                }
                equalsPressed = false
                operationSelected = "/"
                changeOperationSelectedColour(findViewById(buttonPressed.id))
                storeFirstOperator()
                resetDisplay()
                lastOperationSelected = "/"
            }

            binding.signBtn.id -> {
                var changeSign = binding.resultText.text.toString()
                changeSign = if (changeSign[0] == '-') {
                    changeSign.substring(1)
                } else {
                    "-$changeSign"
                }
                binding.resultText.text = changeSign
                //   historyDisplayManager("ngtv")

            }

            binding.percentBtn.id -> {
                var percentage = binding.resultText.text.toString().toDouble()
                percentage /= 100
                if (percentage != percentage.toInt().toDouble()) {
                    binding.resultText.text = percentage.toString()
                } else {
                    binding.resultText.text = percentage.toInt().toString()
                }
                //  historyDisplayManager("%")
            }

            binding.equalBtn.id -> {
                equalsPressed = true
                if (operationSelected == "") {
                    storeFirstOperator()
                }
                storeSecondOperator()
                operate()
                // historyDisplayManager("=${binding.resultText.text}")
                operationSelected = ""
            }
        }
        buttonPressed?.let {
            if (it is Button && it.id in operationButtonsIds) {
                changeOperationSelectedColour(it)
            }
        }
        if (binding.resultText.text.toString() != "0") {
            binding.acBtn.text = "C"
        } else {
            binding.acBtn.text = "AC"
        }

        if (binding.resultText.text.toString() == "Infinity" || binding.resultText.text.toString() == "NaN") {
            dividedByZero = true
        }

        if (dividedByZero) {
            binding.resultText.text = "Error"
        }
        disableButtonsWithErrorResult()

    }

    private fun disableButtonsWithErrorResult() {
        binding.sumBtn.isEnabled = dividedByZero != true
        binding.sustractionBtn.isEnabled = dividedByZero != true
        binding.multiplyBtn.isEnabled = dividedByZero != true
        binding.divisionBtn.isEnabled = dividedByZero != true
        binding.sinBtn?.isEnabled = dividedByZero != true
        binding.sinhBtn?.isEnabled = dividedByZero != true


    }

    private fun factorialCalculations(factorialNumber: Double): Double {
        var factorialResult = factorialNumber
        if (factorialNumber > 1) {
            factorialResult = factorialCalculations(factorialNumber - 1)
        }
        return factorialResult * factorialNumber
    }

    /*private fun historyDisplayManager(charToPrint: String) {
        val regex = "[+\\-/x]".toRegex()
        if (binding.historyText.text == "" && !charToPrint.contains("=") && charToPrint != "ngtv") {
            binding.historyText.text = charToPrint
        } else if (binding.historyText.text == "" && binding.resultText.text == "0" && charToPrint.contains(
                "="
            )
        ) {
            binding.historyText.text = ""
        } else if (regex.containsMatchIn(
                binding.historyText.text.lastOrNull().toString()
            ) && regex.containsMatchIn(charToPrint)
        ) {
            binding.historyText.text = binding.historyText.text.toString().dropLast(1)
            binding.historyText.append(charToPrint)
        } else if (charToPrint == "ngtv") {
            for (i in binding.historyText.length() - 1 downTo 0) {
                if (binding.historyText.length() == 1) {
                    binding.historyText.text = "-${binding.historyText.text}"
                    if (binding.historyText.text.toString() == "-0") {
                        binding.historyText.text = "0"
                    }
                    break
                } else if (binding.historyText.text[i - 1].toString() == "=" && regex.containsMatchIn(
                        binding.historyText.text[i].toString()
                    )
                ) {
                    break
                } else if (regex.containsMatchIn(binding.historyText.text[i].toString())) {
                    var firstSubStr = binding.historyText.text.subSequence(0, i + 1)
                    var secondSubStr =
                        binding.historyText.text.subSequence(i + 1, binding.historyText.length())
                    if (firstSubStr[firstSubStr.length - 1].toString() == "-") {
                        firstSubStr = firstSubStr.dropLast(1)
                        binding.historyText.text = "$firstSubStr$secondSubStr"
                    } else {
                        binding.historyText.text = "$firstSubStr-$secondSubStr"
                    }
                    break
                }
            }
        } else if (charToPrint == "%") {
            for (i in binding.historyText.length() - 1 downTo 0) {
                if (regex.containsMatchIn(binding.historyText.text[i].toString())) {
                    if(binding.historyText.text[i-1].toString() == "E"){
                        var stringCleaned = binding.historyText.text.dropLast(i-1)
                        for(i in binding.resultText.length()-1 downTo 0) {
                            if(binding.resultText.text[i].toString() == "E"){
                                var exponentPartOfResult = binding.resultText.text.substring(i,binding.resultText.length()-1)
                                binding.historyText.text = "$stringCleaned$exponentPartOfResult"
                                break
                            }
                        }

                    } else {
                        binding.historyText.text =
                            binding.historyText.text.dropLast(binding.historyText.length() - i - 2)
                        binding.historyText.append(binding.resultText.text)
                        break
                    }
                } else if (!regex.containsMatchIn(binding.historyText.text[i].toString()) && i == 0) {
                    binding.historyText.text = binding.resultText.text
                    break
                }

            }
        } else {
            binding.historyText.append(charToPrint)
        }
    }*/

    private fun changeOperationSelectedColour(button: Button) {
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        button.setTextColor(ContextCompat.getColor(this, R.color.orange))

    }


    private fun resetOperationSelectedColours() {
        operationButtonsIds.forEach { buttonId ->
            findViewById<Button>(buttonId).setBackgroundColor(
                (ContextCompat.getColor(
                    this, R.color.orange
                ))
            )
            findViewById<Button>(buttonId).setTextColor(
                (ContextCompat.getColor(
                    this, R.color.white
                ))
            )
        }
        binding.powerOfYBtn?.setBackgroundColor(ContextCompat.getColor(this, R.color.medium_grey))
        binding.powerOfYBtn?.setTextColor(ContextCompat.getColor(this, R.color.white))

    }

    private fun operate() {
        var result = 0.0
        if (!dividedByZero) {
            if (operationSelected != "") {
                secondNumberToOperateStored = secondNumberToOperate
            }
            if (operationSelected == "" && lastOperationSelected != "") {
                operationSelected = lastOperationSelected
                secondNumberToOperate = secondNumberToOperateStored
            }

            when (operationSelected) {
                "+" -> {
                    result = firstNumberToOperate + secondNumberToOperate
                }

                "-" -> {
                    result = firstNumberToOperate - secondNumberToOperate
                }

                "x" -> {
                    result = firstNumberToOperate * secondNumberToOperate
                }

                "/" -> {
                    result = firstNumberToOperate / secondNumberToOperate
                }

                "!" -> {
                    result = if (binding.resultText.text.toString() == "0") {
                        1.0
                    } else if (binding.resultText.text.contains(".")) {
                        Gamma.gamma(binding.resultText.text.toString().toDouble() + 1)
                    } else {
                        factorialCalculations(binding.resultText.text.toString().toDouble())
                    }
                }

                "squared" -> {
                    result = binding.resultText.text.toString().toDouble().pow(2.0)
                }

                "cubed" -> {
                    result = binding.resultText.text.toString().toDouble().pow(3.0)
                }

                "xToTheY" -> {
                    result = firstNumberToOperate.pow(secondNumberToOperate)
                }

                "tenToTheX" -> {
                    result = 10.0.pow(binding.resultText.text.toString().toDouble())
                }

                "sqrt" -> {
                    result = sqrt(binding.resultText.text.toString().toDouble())
                }

                "cbrt" -> {
                    result = cbrt(binding.resultText.text.toString().toDouble())
                }

                "n" -> {
                    result = ln(binding.resultText.text.toString().toDouble())
                }

                "lg" -> {
                    result = log10(binding.resultText.text.toString().toDouble())
                }

                "sin" -> {
                    result = if (radiansMode) {
                        sin(binding.resultText.text.toString().toDouble())
                    } else {
                        sin(binding.resultText.text.toString().toDouble() * Math.PI / 180)
                    }
                }

                "cos" -> {
                    result = if (radiansMode) {
                        cos(binding.resultText.text.toString().toDouble())
                    } else {
                        cos(binding.resultText.text.toString().toDouble() * Math.PI / 180)
                    }
                }

                "tan" -> {
                    result = if (radiansMode) {
                        tan(binding.resultText.text.toString().toDouble())
                    } else {
                        tan(binding.resultText.text.toString().toDouble() * Math.PI / 180)
                    }
                }

                "sinh" -> {
                    result = if (radiansMode) {
                        sinh(binding.resultText.text.toString().toDouble())
                    } else {
                        sinh(binding.resultText.text.toString().toDouble() * Math.PI / 180)
                    }
                }

                "cos" -> {
                    result = if (radiansMode) {
                        cosh(binding.resultText.text.toString().toDouble())
                    } else {
                        cosh(binding.resultText.text.toString().toDouble() * Math.PI / 180)
                    }
                }

                "tanh" -> {
                    result = if (radiansMode) {
                        tanh(binding.resultText.text.toString().toDouble())
                    } else {
                        tanh(binding.resultText.text.toString().toDouble() * Math.PI / 180)
                    }
                }

                "rnd" -> {
                    result = Random.nextDouble()
                }

            }
        } else {
            binding.resultText.text = getString(R.string.dividedByZeroError)
            //binding.historyText.text = getString(R.string.dividedByZeroError)
        }
        operationSelected = ""
        if (binding.resultText.text != getString(R.string.dividedByZeroError)) {
            if (result != result.toInt().toDouble()) {
                binding.resultText.text = result.toString()
            } else {
                binding.resultText.text = result.toInt().toString()
            }
        }
    }

    private fun storeSecondOperator() {
        secondNumberToOperate = binding.resultText.text.toString().toDouble()
        if (secondNumberToOperate == 0.0 && operationSelected == "/") {
            dividedByZero = true
        }
    }

    private fun resetDisplay() {
        acPressed = true
    }

    private fun storeFirstOperator() {
        if (binding.resultText.text != getString(R.string.dividedByZeroError)) {
            firstNumberToOperate = binding.resultText.text.toString().toDouble()
        }
    }

    private fun updateNumberSequence(numberPressed: String) {
        if (operationSelected == "xToTheY") {
            binding.resultText.text = "0"

        }
        numericButtonsIds.forEach { buttonId ->
            findViewById<Button>(buttonId)
            if (equalsPressed) {
                lastOperationSelected = ""
            }
        }
        if (acPressed || binding.resultText.text.toString() == "0" || equalsPressed || memoryPressed) {
            if (binding.resultText.text.toString() == "0") {
                secondNumberToOperate = 0.0
                secondNumberToOperateStored = 0.0
            }
            binding.resultText.text = numberPressed
            acPressed = false
            equalsPressed = false
            memoryPressed = false
        } else {
            binding.resultText.append(numberPressed)
        }
    }


}