package com.example.proyectoapis.viewmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ciudades")
data class CiudadEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // ID autogenerado
    val nombre: String, // Nombre de la ciudad
    val temperatura: Double, // Temperatura
    val icono: String // URL del icono del clima
)
