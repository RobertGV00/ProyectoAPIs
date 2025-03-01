package com.example.proyectoapis.view.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.proyectoapis.R
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.proyectoapis.viewmodel.ClimaViewModel
import androidx.lifecycle.viewmodel.compose.viewModel





@Composable
fun PantallaClima(viewModel: ClimaViewModel = viewModel()) {

    // Estado para la ciudad introducida por el usuario
    var ciudad by remember { mutableStateOf(TextFieldValue("")) }

    // Observamos los estados del ViewModel
    val clima = viewModel.climaActual.observeAsState().value
    val cargando = viewModel.cargando.observeAsState(false).value
    val mensajeError = viewModel.mensajeError.observeAsState().value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Campo de texto mejorado para introducir la ciudad
        TextField(
            value = ciudad.text,
            onValueChange = { ciudad = TextFieldValue(it) },
            label = { Text(stringResource(R.string.introducir_ciudad)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para obtener el clima
        Button(
            onClick = {
                println("Botón presionado. Ciudad: ${ciudad.text}")
                if (ciudad.text.isNotEmpty()) {
                    viewModel.obtenerClima(ciudad.text)
                }
            }
        ) {
            Text(text = stringResource(R.string.boton_obtener_clima))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Indicador de carga
        if (cargando) {
            CircularProgressIndicator()
        }

        // Mostrar datos del clima
        clima?.let {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "${stringResource(R.string.ciudad)} ${it.name}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "${stringResource(R.string.temperatura)} ${it.main.temp} ${
                        stringResource(R.string.grados)}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "${stringResource(R.string.temperatura_min)} ${it.main.temp_min}")
                    Text(text = "${stringResource(R.string.temperatura_max)} ${it.main.temp_max}")
                    Text(text = "${stringResource(R.string.humedad)} ${it.main.humidity}")
                    Text(text = "${stringResource(R.string.Sensacion_termica)} ${it.main.feels_like}")
                    Text(text = "${stringResource(R.string.descripcion_clima)} ${it.weather[0].description}", style = MaterialTheme.typography.bodyMedium)

                    AsyncImage(
                        model = "https://openweathermap.org/img/wn/${clima.weather[0].icon}@2x.png",
                        contentDescription = "Icono del clima",
                        modifier = Modifier.size(112.dp)
                    )
                }
            }

        }


        // Mensaje de error
        mensajeError?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}