package com.example.laba2.adapter

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.laba2.R
import com.example.laba2.database.entities.RecipeDB
import com.example.laba2.fragments.Listener

class AdapterListRecipes (
    private val recipeList: List<RecipeDB>,
    private val activity: Activity,
    private val update: () -> Unit
): RecyclerView.Adapter<AdapterListRecipes.RecipeViewHolder>(), Listener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recipe_card,parent,false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.recipe_dif.text = recipeList[position].Difficulty.toString()
        holder.recipe_cal.text = recipeList[position].Calorie.toString()
        holder.recipe_name.text = recipeList[position].Name
        holder.itemView.setOnClickListener{
            onClick(recipeList[position], it)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recipe_name: TextView
        var recipe_dif: TextView
        var recipe_cal: TextView

        init {
            recipe_name= itemView.findViewById(R.id.name_recipe)
            recipe_dif = itemView.findViewById(R.id.Difficulty)
            recipe_cal = itemView.findViewById(R.id.Calorie)
        }
    }
}