package com.example.laba2.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.laba2.R

class RecipeAdapter(private val recipes: Array<String>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName: TextView = itemView.findViewById(R.id.name_recipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_card, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.recipeName.text = recipe
        holder.itemView.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("name", recipe)
            bundle.putString("Calorie", "")
            bundle.putString("Difficulty", "")
            bundle.putString("Time", "")
            bundle.putString("Ingredients", "")

            Navigation.findNavController(it).navigate(R.id.selected, bundle)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}