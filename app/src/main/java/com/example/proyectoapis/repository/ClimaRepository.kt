package com.example.proyectoapis.repository

import com.example.proyectoapis.api.RetrofitCliente
import com.example.proyectoapis.model.RespuestaClima

class ClimaRepository {

    //funcion para obtener el clima actual de una ciudad:

    suspend fun obtenerClimaActual(ciudad: String, apiKey: String): RespuestaClima {
        return RetrofitCliente.servicioApi.obtenerClimaActual(ciudad, apiKey)
    }
}