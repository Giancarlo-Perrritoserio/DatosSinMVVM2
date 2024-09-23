package com.example.datossinmvvm

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HorarioDao {
    @Query("SELECT * FROM Horario")
    suspend fun getAll(): List<Horario>

    @Insert
    suspend fun insert(horario: Horario)

    @Delete
    suspend fun delete(horario: Horario)
}
