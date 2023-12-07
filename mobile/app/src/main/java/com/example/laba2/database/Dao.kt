package com.example.laba2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.laba2.database.entities.FavoriteUserRecipe
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

    @Query("SELECT recipes.* FROM recipes " +
            "INNER JOIN favorite_user_recipe ON recipes.idRecipe = favorite_user_recipe.idRecipe " +
            "WHERE favorite_user_recipe.idUser = :userId")
    fun getFavoriteRecipesByUserId(userId: Int): List<RecipeDB>
    @Insert
    fun insertToFavoriteRecipes(r: FavoriteUserRecipe)

    @Query("SELECT COUNT(*) FROM favorite_user_recipe WHERE idUser = :userId AND idRecipe = :recipeId")
    fun checkFavoriteRecipeExist(userId: Int, recipeId: Int): Boolean

    @Query("DELETE FROM favorite_user_recipe WHERE idUser = :userId AND idRecipe = :recipeId")
    fun deleteFavoriteRecipesByUserId(userId: Int, recipeId: Int)
}