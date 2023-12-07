package com.example.laba2.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laba2.R
import com.example.laba2.adapter.AdapterListRecipes
import com.example.laba2.api.Common
import com.example.laba2.database.DataBase
import com.example.laba2.database.entities.FavoriteUserRecipe
import com.example.laba2.database.entities.RecipeDB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class ListRecipes : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_list_recipes, container, false)
        lateinit var list: List<RecipeDB>
        val db = DataBase.getDatabase(requireContext())
        Common.retrofitService.getRecipes().enqueue(object : Callback<List<RecipeDB>> {
            override fun onFailure(call: Call<List<RecipeDB>>, t: Throwable) {}
            override fun onResponse(call: Call<List<RecipeDB>>, response: Response<List<RecipeDB>>) {
                println("Успех ${response.body()}")
                list = response.body()!!
                thread {
                    if (db.getDao().getCountRecipes() == 0) {
                        list.forEach {
                            db.getDao().insertRecipes(it)
                        }
                    }
                    val listFromDb = db.getDao().getAllRecipes()
                    val rv = view.findViewById<RecyclerView>(R.id.list_recieps_rv)
                    lateinit var adapter: AdapterListRecipes

                    adapter = AdapterListRecipes(listFromDb, requireActivity()) { adapter.notifyDataSetChanged() }
                    requireActivity().runOnUiThread {
                        rv.layoutManager =
                            GridLayoutManager(requireContext(), 2)
                        rv.adapter = adapter
                    }
                }
            }
        })
        return view
    }
}

interface Listener {
    fun onClick(recipe: RecipeDB, view: View) {
        val bundle = Bundle()
        bundle.putString("name", recipe.Name)
        bundle.putString("Calorie", recipe.Calorie.toString())
        bundle.putString("Difficulty", recipe.Difficulty.toString())
        bundle.putString("Time", recipe.Time.toString())
        bundle.putString("Ingredients", recipe.Ingredients)

        Navigation.findNavController(view).navigate(R.id.selected, bundle)
    }

    fun onLongClick(recipe: RecipeDB, activity: Activity){
        thread {
            val id = activity.getSharedPreferences("user", Context.MODE_PRIVATE).getInt("id",0)
            val dao = DataBase.getDatabase(activity).getDao()
            if (!dao.checkFavoriteRecipeExist(id, recipe.idRecipe!!)){
                activity.runOnUiThread {
                    AlertDialog.Builder(activity).setCancelable(true)
                        .setMessage(recipe.Name)
                        .setTitle("Добавить рецепт в список любимых рецептов?")
                        .setNegativeButton("отмена") { _, _ -> }
                        .setPositiveButton("в избранное") { _, _ ->
                            thread { dao.insertToFavoriteRecipes(FavoriteUserRecipe(id, recipe.idRecipe))
                                println(dao.getFavoriteRecipesByUserId(id)) }
                        }
                        .create().show()
                }
            }
            else{
                activity.runOnUiThread {
                    AlertDialog.Builder(activity).setCancelable(true)
                        .setMessage(recipe.Name)
                        .setTitle("Удалить рецепт из списка любимых рецептов?")
                        .setNegativeButton("отмена") { _, _ -> }
                        .setPositiveButton("удалить") { _, _ ->
                            thread {  dao.deleteFavoriteRecipesByUserId(id, recipe.idRecipe) }
                        }.create().show()
                }
            }
        }
    }
}