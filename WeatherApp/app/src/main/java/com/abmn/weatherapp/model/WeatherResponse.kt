package com.abmn.weatherapp.data.model

data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>
)

data class Main(
    val temp: Float
)

data class Weather(
    val description: String
)
