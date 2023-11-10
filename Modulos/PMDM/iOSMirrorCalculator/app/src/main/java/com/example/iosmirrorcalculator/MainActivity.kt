package com.example.iosmirrorcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.iosmirrorcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.acBtn.setOnClickListener(this)
        binding.zeroNumberBtn.setOnClickListener(this)
        binding.oneNumberBtn.setOnClickListener(this)
        binding.twoNumberBtn.setOnClickListener(this)
        binding.threeNumberBtn.setOnClickListener(this)
        binding.fourNumberBtn.setOnClickListener(this)
        binding.fiveNumberBtn.setOnClickListener(this)
        binding.sixNumberBtn.setOnClickListener(this)
        binding.sevenNumberBtn.setOnClickListener(this)
        binding.eightNumberBtn.setOnClickListener(this)
        binding.nineNumberBtn.setOnClickListener(this)
        binding.divisionBtn.setOnClickListener(this)
        binding.additionBtn.setOnClickListener(this)
        binding.multiplyBtn.setOnClickListener(this)
        binding.sustractionBtn.setOnClickListener(this)
        binding.equalBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.acBtn.id->{
                binding.resultText.text = "0"
            }
            binding.oneNumberBtn.id->{
                binding.resultText.text = "1"
            }
            binding.twoNumberBtn.id->{
                binding.resultText.text = "2"
            }
            binding.threeNumberBtn.id->{
                binding.resultText.text = "3"
            }
            binding.fourNumberBtn.id->{
                binding.resultText.text = "4"
            }
            binding.fiveNumberBtn.id->{
                binding.resultText.text = "5"
            }
            binding.sixNumberBtn.id->{
                binding.resultText.text = "6"
            }
            binding.sevenNumberBtn.id->{
                binding.resultText.text = "7"
            }
            binding.eightNumberBtn.id->{
                binding.resultText.text = "8"
            }
            binding.nineNumberBtn.id->{
                binding.resultText.text = "9"
            }
            binding.zeroNumberBtn.id->{
                binding.resultText.text = "0"
            }
        }
    }
}