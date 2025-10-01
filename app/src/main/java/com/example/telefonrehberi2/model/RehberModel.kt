package com.example.telefonrehberi2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RehberModel(
    val name: String,
    val phoneNumber: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
