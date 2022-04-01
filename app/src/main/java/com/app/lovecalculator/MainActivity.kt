package com.app.lovecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var fname:EditText
    private lateinit var sname:EditText
    private lateinit var calculate:Button
    private lateinit var loading: ConstraintLayout
    private lateinit var requestQueue: RequestQueue
    private lateinit var animataionLoading: LottieAnimationView
    private lateinit var result: LinearLayout
    private lateinit var percentage: TextView
    private lateinit var msg: TextView
    private lateinit var okButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fname = findViewById(R.id.fname)
        sname = findViewById(R.id.sname)
        calculate = findViewById(R.id.cal_btn)
        loading = findViewById(R.id.loading)
        animataionLoading = findViewById(R.id.animationView)
        requestQueue = Volley.newRequestQueue(this)
        result = findViewById(R.id.result)
        percentage = findViewById(R.id.percentage)
        msg = findViewById(R.id.msg)
        okButton = findViewById(R.id.ok)

        calculate.setOnClickListener {

            if(fname.text.isEmpty() || sname.text.isEmpty()){
                Toast.makeText(this, "Missing Fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loading.visibility = View.VISIBLE

            val stringRequest = object : StringRequest(Method.GET,"https://love-calculator.p.rapidapi.com/getPercentage?fname=${fname.text.toString()}&sname=${sname.text.toString()}",
                    Response.Listener {
//                        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                        animataionLoading.visibility = View.GONE
                        result.visibility = View.VISIBLE
                        val jsonObject = JSONObject(it)

                        percentage.text = jsonObject.getString("percentage")
                        msg.text = jsonObject.getString("result")

                    },Response.ErrorListener {
                        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }){
                override fun getHeaders(): MutableMap<String, String> {
                    val hashMap = HashMap<String, String>()
                    hashMap["x-rapidapi-host"]="love-calculator.p.rapidapi.com"
                    hashMap["x-rapidapi-key"]="ba3ca290d3msh1445cc67dfa5388p186870jsn8fcfa463a03c"
                    return hashMap
                }

            }
            requestQueue.add(stringRequest)
        }
        okButton.setOnClickListener {
            result.visibility = View.GONE
            animataionLoading.visibility = View.VISIBLE
            loading.visibility = View.GONE

            fname.text.clear()
            sname.text.clear()
        }

    }

}