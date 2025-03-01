package com.example.proyectoapis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.proyectoapis.view.ui.PantallaClima


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PantallaClima()  // Llamamos al Composable que acabamos de crear
            }
        }
    }
}
