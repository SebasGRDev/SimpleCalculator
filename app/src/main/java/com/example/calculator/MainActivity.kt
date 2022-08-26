package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.calculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnClear.setOnClickListener {
            clearInputText()
            binding.tvOutput.text = ""
        }
        btnBracketLeft.setOnClickListener { tvInput.text = addToInputText("(") }
        btnBracketRight.setOnClickListener { tvInput.text = addToInputText(")") }
        btnPoint.setOnClickListener { tvInput.text = addToInputText(".") }
        btnSum.setOnClickListener { tvInput.text = addToInputText("+") }
        btnRest.setOnClickListener { tvInput.text = addToInputText("-") }
        btnDiv.setOnClickListener { tvInput.text = addToInputText("÷") }
        btnMulti.setOnClickListener { tvInput.text = addToInputText("×") }
        btn0.setOnClickListener { tvInput.text = addToInputText("0") }
        btn1.setOnClickListener { tvInput.text = addToInputText("1") }
        btn2.setOnClickListener { tvInput.text = addToInputText("2") }
        btn3.setOnClickListener { tvInput.text = addToInputText("3") }
        btn4.setOnClickListener { tvInput.text = addToInputText("4") }
        btn5.setOnClickListener { tvInput.text = addToInputText("5") }
        btn6.setOnClickListener { tvInput.text = addToInputText("6") }
        btn7.setOnClickListener { tvInput.text = addToInputText("7") }
        btn8.setOnClickListener { tvInput.text = addToInputText("8") }
        btn9.setOnClickListener { tvInput.text = addToInputText("9") }
        btnResult.setOnClickListener {
            showResult()
            clearInputText()
        }
    }
    private fun clearInputText(){
        tvOpView.text = binding.tvInput.text
        binding.tvInput.text = ""

    }

    private fun addToInputText(buttonValue: String): String {
        return "${tvInput.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        var expression = tvInput.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult(){
        try {
            val exception = getInputExpression()
            val result = Expression(exception).calculate()
            if (result.isNaN()){
                //Show Error message
                tvOutput.text = "ERROR"
                tvOutput.setTextColor(ContextCompat.getColor(this, R.color.redColor))
            } else {
                //Show result
                tvOutput.text = DecimalFormat("0.##").format(result).toString()
                tvOutput.setTextColor(ContextCompat.getColor(this, R.color.greenColor))
            }
        } catch (e: Exception){
            tvOutput.text = "ERROR"
            tvOutput.setTextColor(ContextCompat.getColor(this, R.color.redColor))
        }
    }
}