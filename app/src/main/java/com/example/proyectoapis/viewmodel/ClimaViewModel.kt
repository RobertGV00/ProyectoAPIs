package com.example.proyectoapis.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoapis.model.RespuestaClima
import com.example.proyectoapis.repository.ClimaRepository
import kotlinx.coroutines.launch


class ClimaViewModel : ViewModel() {
    private val repositorioClima = ClimaRepository()

    //estados:
    private val _climaActual = MutableLiveData<RespuestaClima>()
    val climaActual: LiveData<RespuestaClima> get() = _climaActual

    private val _cargando = MutableLiveData<Boolean>()
    val cargando: LiveData<Boolean> get() = _cargando

    private val _mensajeError = MutableLiveData<String>()
    val mensajeError: LiveData<String> get() = _mensajeError

    fun obtenerClima(ciudad: String) {
        _cargando.value = true
        viewModelScope.launch {
            try {
                val apiKey = "f9ce626e93bef28713fb264cb662814a"
                val ciudadPrueba = "Madrid"
                val urlCompleta = "https://api.openweathermap.org/data/2.5/weather?q=$ciudad&appid=$apiKey&units=metric"
                println("URL Completa: $urlCompleta")
                val clima = repositorioClima.obtenerClimaActual(ciudad, apiKey)
                _climaActual.value = clima
                _cargando.value = false
                println("clima obtenido: $clima")
            } catch (e: Exception) {
                _mensajeError.value = "Error: ${e.message}"
                _cargando.value = false
            }
        }
    }
}