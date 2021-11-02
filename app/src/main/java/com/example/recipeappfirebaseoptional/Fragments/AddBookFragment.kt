package com.example.recipeappfirebaseoptional.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.recipeappfirebaseoptional.Models.ManViewModel
import com.example.recipeappfirebaseoptional.R


class AddBookFragment : Fragment() {
    lateinit var manViewModel: ManViewModel
    lateinit var edAuthor: EditText
    lateinit var edIngredients: EditText
    lateinit var edInstructions: EditText
    lateinit var edTitle: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.fragment_add_book, container, false)
        edAuthor=view.findViewById(R.id.edAuthor)
        edIngredients=view.findViewById(R.id.edIngredients)
        edInstructions=view.findViewById(R.id.edInstructions)
        edTitle=view.findViewById(R.id.edTitle)
        manViewModel= ManViewModel(requireActivity().application)
        view.findViewById<Button>(R.id.btnSave).setOnClickListener {
            manViewModel.addBook(edAuthor.text.toString(), edIngredients.text.toString(), edInstructions.text.toString(), edTitle.text.toString())
            clear()
        }
        view.findViewById<Button>(R.id.btnView).setOnClickListener {
            view.findNavController().navigate(R.id.action_addBookFragment_to_homeFragment)

        }

        // Inflate the layout for this fragment
        return view
    }
    fun clear(){
        edAuthor.text.clear()
        edIngredients.text.clear()
        edTitle.text.clear()
        edInstructions.text.clear()
    }


}