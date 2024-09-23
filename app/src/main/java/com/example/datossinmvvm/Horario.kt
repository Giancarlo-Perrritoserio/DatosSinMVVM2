package com.example.datossinmvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Horario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "hora_inicio") val horaInicio: String,
    @ColumnInfo(name = "hora_fin") val horaFin: String,
    @ColumnInfo(name = "mascota_id") val mascotaId: Int // Asumiendo que relacionas horarios con mascotas
)
