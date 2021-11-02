package com.example.recipeappfirebaseoptional.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.recipeappfirebaseoptional.Models.Book
import com.example.recipeappfirebaseoptional.Models.ManViewModel
import com.example.recipeappfirebaseoptional.R


class HomeFragment : Fragment() {
    val manViewModel by lazy { ViewModelProvider(this).get(ManViewModel::class.java) }
    var books=emptyList<Book>()
    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home, container, false)

        // Inflate the layout for this fragment
        manViewModel.getBooks().observe(this,{
                listbooks->books=listbooks
            var text=""
            for (book in books){
                text+="title: ${book.title}\nauthor: ${book.author}\ningredients: ${book.ingredients}\ninstructions:${book.instructions}\n\n"
            }
            view.findViewById<TextView>(R.id.textView).text=text
        })
        view.findViewById<Button>(R.id.btnAdd).setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_addBookFragment)
        }
        return view
    }


}