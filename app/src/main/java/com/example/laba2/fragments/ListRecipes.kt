package com.example.laba2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.example.laba2.BottomNavigationActivity
import com.example.laba2.R
import com.example.laba2.adapter.RecipeAdapter
import com.example.laba2.model.Recipe

class ListRecipes : Fragment() {


    private lateinit var adapter: RecipeAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_list_recipes, container, false)
        val recipeList = resources.getStringArray(R.array.recipes_name)

        recyclerView = view.findViewById(R.id.list_recieps_rv)
        adapter = RecipeAdapter(recipeList)

        recyclerView.adapter = adapter

        return view
    }

}