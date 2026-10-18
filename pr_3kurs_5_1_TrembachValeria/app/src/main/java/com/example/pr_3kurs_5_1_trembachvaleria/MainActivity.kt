package com.example.pr_3kurs_5_1_trembachvaleria

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var cityField: EditText
    private lateinit var tvCity: TextView
    private lateinit var tvTemp: TextView
    private lateinit var tvPressure: TextView
    private lateinit var tvWind: TextView

    private val key = "43c606d9389cb431d83648332f39d74c"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityField = findViewById(R.id.city)
        val btnGet = findViewById<Button>(R.id.btnGet)
        tvCity = findViewById(R.id.tvCity)
        tvTemp = findViewById(R.id.tvTemp)
        tvPressure = findViewById(R.id.tvPressure)
        tvWind = findViewById(R.id.tvWind)

        btnGet.setOnClickListener {
            val city = cityField.text.toString().trim()
            if (city.isNotEmpty()) {
                getResult(city)
            } else {
                Toast.makeText(this, "Введите город", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getResult(city: String) {
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$key&units=metric&lang=ru"

        val queue: RequestQueue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                Log.d("MyLog", "Response: $response")

                val weather: WeatherResponse =
                    Gson().fromJson(response, WeatherResponse::class.java)

                tvCity.text = "Город: ${weather.city}"
                tvTemp.text = "Температура: ${weather.main.temp}°C"
                tvPressure.text = "Давление: ${weather.main.pressure} гПа"
                tvWind.text = "Ветер: ${weather.wind.speed} м/с"
            },
            { error ->
                Log.d("MyLog", "Volley error: ${error.message}")
                tvCity.text = "Ошибка: ${error.message}"
            }
        )

        queue.add(stringRequest)
    }
}