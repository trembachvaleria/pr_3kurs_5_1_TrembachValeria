package com.example.pr_3kurs_5_1_trembachvaleria

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("name") val city: String,
    @SerializedName("main") val main: Main,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("weather") val weather: List<Weather>
)

data class Main(
    @SerializedName("temp") val temp: Double,
    @SerializedName("pressure") val pressure: Int
)

data class Wind(
    @SerializedName("speed") val speed: Double
)

data class Weather(
    @SerializedName("description") val description: String
)