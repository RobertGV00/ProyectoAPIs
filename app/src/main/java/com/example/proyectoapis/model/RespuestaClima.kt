package com.example.proyectoapis.model

data class Main(
    val temp: Double,
    val humidity: Int
)

data class RespuestaClima(
    val main: Main,
    val weather: List<Clima>,
    val name: String
)

data class Clima(
    val description: String,
    val icon: String
)

