package com.example.laba2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.laba2.database.entities.RecipeDB
import com.example.laba2.database.entities.User

@Dao
interface Dao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): List<RecipeDB>
    @Query("SELECT COUNT(*) FROM recipes")
    fun getCountRecipes(): Int
    @Insert
    fun insertRecipes(r: RecipeDB)
    @Insert
    fun createUser(newUser: User)
    @Query("SELECT * FROM users WHERE login = :login")
    fun getUserByLogin(login: String): User
    @Query("SELECT * FROM users WHERE idUser = :id")
    fun getUserById(id: Int): User
    @Query("SELECT COUNT(*) FROM users WHERE login = :login")
    fun checkUserExists(login: String): Boolean

}