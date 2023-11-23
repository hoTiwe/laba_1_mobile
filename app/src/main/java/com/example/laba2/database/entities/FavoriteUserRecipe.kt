package com.example.laba2.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "Favorite_user-recipe",
    primaryKeys = ["idUser", "idRecipe"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["idUser"],
            childColumns = ["idUser"]
        ),
        ForeignKey(
            entity = RecipeDB::class,
            parentColumns = ["idRecipe"],
            childColumns = ["idRecipe"]
        )
    ]
)
data class FavoriteUserRecipe(
    val idUser: Int,
    val idRecipe: Int
)
