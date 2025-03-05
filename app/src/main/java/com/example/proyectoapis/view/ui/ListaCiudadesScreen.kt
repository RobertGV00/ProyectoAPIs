@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.proyectoapis.view.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

// Datos de las ciudades con imágenes
val ciudades = listOf(
    Ciudad("Madrid", "https://media.istockphoto.com/id/1343071828/es/foto/madrid-espa%C3%B1a-horizonte-de-la-ciudad-al-amanecer-en-el-parque-de-el-retiro-con-temporada-de.jpg?s=2048x2048&w=is&k=20&c=6jEbvKY3pW2XE-V_AFfl0R-0yH5xiOjtxW8XPsTOjX4="),
    Ciudad("Green Bay", "https://a.travel-assets.com/findyours-php/viewfinder/images/res70/34000/34849-Green-Bay.jpg"),
    Ciudad("Bucharest", "https://images.unsplash.com/photo-1574974915729-40753c60260d?q=80&w=1973&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    Ciudad("Londres", "https://images.unsplash.com/photo-1543832923-44667a44c804?q=80&w=1944&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
    Ciudad("Cuenca", "https://plus.unsplash.com/premium_photo-1697729801822-c68999fc5e5c?q=80&w=2084&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
)

// Modelo de datos para Ciudad
data class Ciudad(val nombre: String, val imageUrl: String)

@Composable
fun ListaCiudadesScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Selecciona una ciudad") })
    }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(ciudades) { ciudad ->
                CiudadItem(ciudad, navController)
            }
        }
    }
}

@Composable
fun CiudadItem(ciudad: Ciudad, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                navController.navigate("pantallaClima/${ciudad.nombre}") // 🔹 Navega con el nombre de la ciudad seleccionada
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ciudad.imageUrl, // 🔹 Usa AsyncImage en lugar de rememberAsyncImagePainter()
                contentDescription = ciudad.nombre,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = ciudad.nombre,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
