package com.example.laba2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.laba2.R


class SelectedRecipe : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_selected_recipe, container, false)
        view.findViewById<TextView>(R.id.name).text = arguments?.getString("name")
        view.findViewById<TextView>(R.id.difficulty).text = "Уровень сложности: " + arguments?.getString("Difficulty")
        view.findViewById<TextView>(R.id.calorie).text = "Калорийность: " + arguments?.getString("Calorie")
        view.findViewById<TextView>(R.id.ingridient).text = "Ингредиенты: " + arguments?.getString("Ingredients")
        view.findViewById<TextView>(R.id.time).text = "Время приготовления: " + arguments?.getString("Time")
        return view
    }

}