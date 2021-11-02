package com.example.recipeappfirebaseoptional.Models

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ManViewModel(application: Application):AndroidViewModel(application) {
    private val books: MutableLiveData<List<Book>> = MutableLiveData()
    val app=application
    val db=Firebase.firestore
    init {
        db.collection("Books")
            .get()
            .addOnSuccessListener {
                outPut->
                val tempBooks=ArrayList<Book>()
                for (book in outPut){
                    val templist=ArrayList<String>()
                    book.data.map {
                        (key,value)->  templist.add(value.toString())
                    }
                    tempBooks.add(Book(book.id,templist[3],templist[1],templist[0],templist[2]))
                }
                books.postValue(tempBooks)
            }
    }
    fun getBooks():LiveData<List<Book>>{
        return books
    }
    fun addBook(author:String,ingredients:String,instructions:String,title:String){

        if (title.isNotEmpty()&&author.isNotEmpty()&&ingredients.isNotEmpty()&&instructions.isNotEmpty())
        {
            val book= hashMapOf(
                "title" to title,
                "author" to author,
                "ingredients" to ingredients,
                "instructions" to instructions
            )
            db.collection("Books").add(book)
            Toast.makeText(app, "book is added ", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(app, "please complete the fields", Toast.LENGTH_SHORT).show()
        }
    }

}