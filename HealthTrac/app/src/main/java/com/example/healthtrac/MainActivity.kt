package com.example.healthtrac
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var height: Double = 0.0
    private var weight: Double = 0.0
    private var temp: Double = 0.0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.isEnabled = false
        fun EditText.addTextWatcher() {
            this.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?,  start: Int,   count: Int,  after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    when (this@addTextWatcher) {
                        editTextNumberDecimal1 -> {
                            button1.isEnabled = editTextNumberDecimal1.text.isNotEmpty() && editTextNumberDecimal2.text.isNotEmpty()
                        }
                        editTextNumberDecimal2 ->  {
                            button1.isEnabled = editTextNumberDecimal1.text.isNotEmpty() && editTextNumberDecimal2.text.isNotEmpty()
                        }
                    }
                }
            }
            )
        }
        editTextNumberDecimal1.addTextWatcher()
        editTextNumberDecimal2.addTextWatcher()
        textView1.visibility = View.INVISIBLE
        textView2.visibility = View.INVISIBLE
        button1.setOnClickListener {
                height = editTextNumberDecimal1.text.toString().toDouble()
                weight = editTextNumberDecimal2.text.toString().toDouble()
                val res = (weight * 10000) / (height * height)
                textView1.visibility = View.VISIBLE
                temp = String.format("%.2f", res).toDouble()
                if (temp.isNaN() || temp<7.50 || temp > 185.00){
                    textView1.setTextColor(Color.parseColor("#FF0000"))
                    textView1.text = "BMI cannot be less than 7.5 or over 185!!"
                }
                else{
                    if (temp in 7.50..18.49){
                        textView1.setTextColor(Color.parseColor("#FFFF00"))
                        textView1.text = "Your BMI is $temp"
                    }
                    else if (temp in 18.50..24.99){
                        textView1.setTextColor(Color.parseColor("#006400"))
                        textView1.text = "Your BMI is $temp"
                    }
                    else if (temp in 25.00..29.99){
                        textView1.setTextColor(Color.parseColor("#FF8C00"))
                        textView1.text = "Your BMI is $temp"
                    }
                    else if (temp in 30.00..185.00){
                        textView1.setTextColor(Color.parseColor("#8B0000"))
                        textView1.text = "Your BMI is $temp"
                    }
                    else{
                        textView1.setTextColor(Color.parseColor("#FF0000"))
                        textView1.text = "Your BMI is $temp"
                    }
                }
                textView2.visibility = View.VISIBLE
                textView2.text = "You are ${feedback(res)}"
            }
            button2.setOnClickListener {
                editTextNumberDecimal1.text.clear()
                editTextNumberDecimal2.text.clear()
                textView1.visibility = View.INVISIBLE
                textView2.visibility = View.INVISIBLE
            }
        }
    private fun feedback(res: Double): String {
        var fb = ""
        val any = when (res) {
            0.00 -> fb="entering incorrect details.Re-check the entered details."
            in 7.50..18.49 -> fb = "Underweight.You need to add some extra pounds."
            in 18.50..24.99 -> fb = "Healthy.You are fitter than 43% people in the world."
            in 25.00..29.99 -> fb = "Overweight.You need to shed off some extra pounds."
            in 30.00..185.50 -> fb = "Obese!!The hardest step to fitness is the first.Take it now!!"
            else -> fb="entering incorrect details.Re-check the entered details."
        }
        return fb
    }
}




