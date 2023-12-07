package com.example.laba2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.laba2.database.entities.FavoriteUserRecipe
import com.example.laba2.database.entities.RecipeDB
import com.example.laba2.database.entities.User


@Database(entities = [RecipeDB::class, User::class, FavoriteUserRecipe::class],version = 1)
abstract class DataBase: RoomDatabase() {

    abstract fun getDao(): Dao
    companion object{
        fun getDatabase(context: Context): DataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                "mydb.db"
            ).build()
        }
    }
}