package com.example.datossinmvvm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Room
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHorarios() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        MascotasDatabase::class.java,
        "mascotas_db"
    ).build()
    val dao = db.horarioDao()
    val coroutineScope = rememberCoroutineScope()
    var horaInicio by remember { mutableStateOf("") }
    var horaFin by remember { mutableStateOf("") }
    var listaHorarios = remember { mutableStateOf(listOf<Horario>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Horarios de Seguimiento") },
                actions = {
                    Button(onClick = {
                        coroutineScope.launch {
                            val nuevoHorario = Horario(horaInicio = horaInicio, horaFin = horaFin, mascotaId = 1) // Reemplaza con ID de mascota real
                            dao.insert(nuevoHorario)
                            listaHorarios.value = dao.getAll()
                            horaInicio = ""
                            horaFin = ""
                        }
                    }) {
                        Text("Agregar")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                TextField(value = horaInicio, onValueChange = { horaInicio = it }, label = { Text("Hora de Inicio") })
                TextField(value = horaFin, onValueChange = { horaFin = it }, label = { Text("Hora de Fin") })
                Button(onClick = {
                    coroutineScope.launch {
                        listaHorarios.value = dao.getAll()
                    }
                }) {
                    Text("Listar Horarios")
                }
                listaHorarios.value.forEach { horario ->
                    Text("Horario: ${horario.horaInicio} - ${horario.horaFin}")
                }
            }
        }
    )
}
