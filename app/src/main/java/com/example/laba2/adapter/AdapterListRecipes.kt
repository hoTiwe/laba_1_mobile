package com.example.laba2.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.laba2.R
import com.example.laba2.model.Recipe

class AdapterListRecipes (
    private val recipeList: List<Recipe>
): RecyclerView.Adapter<AdapterListRecipes.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recipe_card,parent,false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.recipe_dif.text = recipeList[position].Difficulty.toString()
        holder.recipe_cal.text = recipeList[position].Calorie.toString()
        holder.recipe_name.text = recipeList[position].Name
        holder.itemView.setOnClickListener{view ->
            val bundle = Bundle()
            bundle.putString("name", recipeList[position].Name)
            bundle.putString("Calorie", recipeList[position].Calorie.toString())
            bundle.putString("Difficulty", recipeList[position].Difficulty.toString())
            bundle.putString("Time", recipeList[position].Time.toString())
            bundle.putString("Ingredients", recipeList[position].Ingredients)

            Navigation.findNavController(view).navigate(R.id.selected, bundle)
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