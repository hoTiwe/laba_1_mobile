package com.example.laba2.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recipes")
data class RecipeDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idRecipe")
    val idRecipe: Int? = null,

    @ColumnInfo(name = "Name")
    val Name: String,

    @ColumnInfo(name = "Calorie")
    val Calorie: Int,

    @ColumnInfo(name = "Time")
    val Time: Int,

    @ColumnInfo(name = "Ingredients")
    val Ingredients: String,

    @ColumnInfo(name = "Difficulty")
    val Difficulty: Int,
)