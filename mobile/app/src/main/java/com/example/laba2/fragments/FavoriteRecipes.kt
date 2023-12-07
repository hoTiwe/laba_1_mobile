package com.example.laba2.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.laba2.R
import com.example.laba2.adapter.AdapterListRecipes
import com.example.laba2.database.DataBase
import kotlin.concurrent.thread


class FavoriteRecipes : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_favourite_recipes, container, false)
        thread {
            val list = DataBase.getDatabase(requireContext()).getDao().getFavoriteRecipesByUserId(
                requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getInt("id", 0)
            )
            lateinit var adapter: AdapterListRecipes

            adapter = AdapterListRecipes(list, requireActivity()) { adapter.notifyDataSetChanged() }
            requireActivity().runOnUiThread {
                view!!.findViewById<RecyclerView>(R.id.list_favorite_recipes_rv).adapter = adapter
            }
        }
        return view
    }
}