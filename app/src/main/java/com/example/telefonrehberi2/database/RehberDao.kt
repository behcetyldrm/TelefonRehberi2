package com.example.telefonrehberi2.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.telefonrehberi2.model.RehberModel

@Dao
interface RehberDao {

    @Query("SELECT * FROM RehberModel")
    suspend fun getAllData() : List<RehberModel>

    @Query("SELECT * FROM RehberModel WHERE id= :id")
    suspend fun getSelectedData(id: Int) : RehberModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(kisi: RehberModel)

    @Update
    suspend fun updateData(kisi: RehberModel)

    @Delete
    suspend fun deleteData(kisi: RehberModel)
}