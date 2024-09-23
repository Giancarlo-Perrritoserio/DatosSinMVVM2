package com.example.datossinmvvm

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Horario::class], version = 1)
abstract class MascotasDatabase : RoomDatabase() {
    abstract fun horarioDao(): HorarioDao
}
