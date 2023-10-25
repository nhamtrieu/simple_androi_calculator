package com.example.roll_dice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.ViewBinding
import com.example.roll_dice.databinding.ActivityMainBinding
import java.time.Duration
import javax.xml.xpath.XPathExpression

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding(layoutInflater)
        setContentView(binding.root)

        val data = MutableLiveData<String>()
        var working = ""
        var lastNumber = ""
        var preNumber = ""

        binding.apply {
            btnDivide.setOnClickListener {
                lastNumber = ""
                working += '/'
                data.value = working
            }
            btnMinus.setOnClickListener {
                working += "-"
                lastNumber = ""

                data.value = working
            }
            btnMultiply.setOnClickListener {
                working += '*'
                lastNumber = ""

                data.value = working
            }
            btnPlus.setOnClickListener {
                working += '+'
                lastNumber = ""

                data.value = working
            }

            btn1.setOnClickListener {
                working += '1'
                lastNumber += '1'

                data.value = working
            }
            btn2.setOnClickListener {
                working += '2'
                lastNumber += '2'
                data.value = working
            }
            btn3.setOnClickListener {
                working += '3'
                lastNumber += '3'
                data.value = working
            }
            btn4.setOnClickListener {
                working += '4'
                lastNumber += '4'
                data.value = working
            }

            btn5.setOnClickListener {
                working += '5'
                lastNumber += '5'
                data.value = working
            }

            btn6.setOnClickListener {
                working += '6'
                lastNumber += '6'
                data.value = working
            }

            btn7.setOnClickListener {
                working += '7'
                lastNumber += '7'
                data.value = working
            }
            btn8.setOnClickListener{
                working += '8'
                lastNumber += '8'
                data.value = working
            }
            btn9.setOnClickListener {
                working += '9'
                lastNumber += '9'
                data.value = working
            }
            btn0.setOnClickListener {
                if (lastNumber.isEmpty()){
                    lastNumber = "0"
                    return@setOnClickListener
                }
                working += '0'
                lastNumber += '0'
                data.value = working
            }

            btnEq.setOnClickListener {
                try {
                    working = ExpressionEvaluation.evaluate(working).toString()
                    lastNumber = working
                    data.value = working
                }
                catch (e: Exception){
                    Toast.makeText(this@MainActivity, "Invalid expression", Toast.LENGTH_LONG).show()
                }

            }
            btnC.setOnClickListener {
                working = ""
                lastNumber = ""
                data.value = working
            }
            btnCe.setOnClickListener {
                if (lastNumber.isNotEmpty()){
                    working = working.dropLast(lastNumber.length)
                    lastNumber = ""
                    data.value = working
                }
            }
            btnBs.setOnClickListener {
                if (lastNumber.isEmpty()){
                    return@setOnClickListener
                }
                lastNumber = lastNumber.dropLast(1)
                working = working.dropLast(1)
                data.value = working
            }
        }
        data.observe(this) {
            binding.resultTv.text = it
        }
    }

    private fun getViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }
}