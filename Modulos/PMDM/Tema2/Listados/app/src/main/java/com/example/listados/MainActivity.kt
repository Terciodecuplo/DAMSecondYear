package com.example.listados

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.listados.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener(this)
        binding.cleanBtn.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.loginBtn.id -> {
                if (!binding.editMail.text.isEmpty() &&
                    !binding.editPass.text.isEmpty() &&
                    findViewById<RadioButton>(binding.stateGroup.checkedRadioButtonId) != null
                ) {
                    val userMail = binding.editMail.text.toString()
                    val userPassword = binding.editPass.text.toString()
                    val userRole = binding.itemList.selectedItem.toString()
                    val userStateLogin =
                        findViewById<RadioButton>(binding.stateGroup.checkedRadioButtonId).text.toString()
                    if(userMail.equals("jose@gmail.com") &&
                        userPassword.equals("1234") &&
                        !userStateLogin.equals("")){
                        Snackbar.make(
                            binding.root,
                            resources.getString(R.string.login_correct_msg),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else {
                        Snackbar.make(
                            binding.root,
                            resources.getString(R.string.login_incorrect_msg),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Snackbar.make(
                        binding.root,
                        resources.getString(R.string.login_incomplete_msg),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

            binding.cleanBtn.id -> {
                binding.editMail.setText("")
                binding.editPass.setText("")
                binding.itemList.setSelection(0)
                binding.stateGroup.clearCheck()
            }
        }
    }


}