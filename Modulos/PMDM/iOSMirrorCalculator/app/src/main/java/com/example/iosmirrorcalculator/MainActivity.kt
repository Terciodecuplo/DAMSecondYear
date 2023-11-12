package com.example.iosmirrorcalculator

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.iosmirrorcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var firstNumberToOperate = 0.0
    private var secondNumberToOperate = 0.0
    private var secondNumberToOperateStored = 0.0
    private var acPressed = false
    private var operationSelected = ""
    private var lastOperationSelected = ""
    private var dividedByZero = false
    private val operationButtonsIds = setOf(
        R.id.sumBtn,
        R.id.sustractionBtn,
        R.id.multiplyBtn,
        R.id.divisionBtn
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
            binding.divisionBtn
        )

        allButtonsBinds.forEach { button -> button.setOnClickListener(this) }

    }

    override fun onClick(buttonPressed: View?) {
        resetOperationSelectedColours()
        when (buttonPressed?.id) {

            binding.acBtn.id -> {
                acPressed = true
                dividedByZero = false
                firstNumberToOperate = 0.0
                secondNumberToOperate = 0.0
                secondNumberToOperateStored = 0.0
                lastOperationSelected = ""
                binding.resultText.text = "0"
            }

            binding.oneNumberBtn.id -> updateNumberSequence("1")

            binding.twoNumberBtn.id -> updateNumberSequence("2")

            binding.threeNumberBtn.id -> updateNumberSequence("3")

            binding.fourNumberBtn.id -> updateNumberSequence("4")

            binding.fiveNumberBtn.id -> updateNumberSequence("5")

            binding.sixNumberBtn.id -> updateNumberSequence("6")

            binding.sevenNumberBtn.id -> updateNumberSequence("7")

            binding.eightNumberBtn.id -> updateNumberSequence("8")

            binding.nineNumberBtn.id -> updateNumberSequence("9")

            binding.zeroNumberBtn.id -> updateNumberSequence("0")

            binding.decimalBtn.id -> {
                if (!binding.resultText.text.toString().contains(".")) {
                    binding.resultText.append(".")
                }
            }

            binding.sumBtn.id -> {
                changeOperationSelectedColour(findViewById(buttonPressed.id))
                storeFirstOperator()
                resetDisplay()
                operationSelected = "+"
                lastOperationSelected = "+"
            }

            binding.sustractionBtn.id -> {
                changeOperationSelectedColour(findViewById(buttonPressed.id))
                storeFirstOperator()
                resetDisplay()
                operationSelected = "-"
                lastOperationSelected = "-"
            }

            binding.multiplyBtn.id -> {
                changeOperationSelectedColour(findViewById(buttonPressed.id))
                storeFirstOperator()
                resetDisplay()
                operationSelected = "x"
                lastOperationSelected = "x"
            }

            binding.divisionBtn.id -> {
                changeOperationSelectedColour(findViewById(buttonPressed.id))
                storeFirstOperator()
                resetDisplay()
                operationSelected = "/"
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

            }

            binding.percentBtn.id -> {
                var percentage = binding.resultText.text.toString().toDouble()
                percentage /= 100
                if (percentage != percentage.toInt().toDouble()) {
                    binding.resultText.text = percentage.toString()
                } else {
                    binding.resultText.text = percentage.toInt().toString()
                }
            }

            binding.equalBtn.id -> {
                if (operationSelected == "") {
                    storeFirstOperator()
                } else {
                    storeSecondOperator()
                }
                operate()
                firstNumberToOperate = 0.0
                secondNumberToOperate = 0.0
                operationSelected = ""
            }
        }
        buttonPressed?.let {
            if (it is Button && it.id in operationButtonsIds) {
                changeOperationSelectedColour(it)
            }
        }

    }

    private fun changeOperationSelectedColour(button: Button) {
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        button.setTextColor(ContextCompat.getColor(this, R.color.orange))
    }

    private fun resetOperationSelectedColours() {
        operationButtonsIds.forEach { buttonId ->
            findViewById<Button>(buttonId).setBackgroundColor(
                (ContextCompat.getColor(
                    this,
                    R.color.orange
                ))
            )
            findViewById<Button>(buttonId).setTextColor(
                (ContextCompat.getColor(
                    this,
                    R.color.white
                ))
            )
        }

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
            }
        } else {
            binding.resultText.text = getString(R.string.dividedByZeroError)
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
        if (acPressed || binding.resultText.text == "0") {
            binding.resultText.text = numberPressed
            acPressed = false
        } else {
            binding.resultText.append(numberPressed)
        }
    }


}