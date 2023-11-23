package com.example.laba2.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="idUser") val idUser: Int? = null,

    @ColumnInfo(name = "login") val login: String,

    @ColumnInfo(name = "password") val password: String,
)