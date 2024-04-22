package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Deletactivity : AppCompatActivity() {



    private lateinit var adapter: adepter2
    private lateinit var dbdelet : databasedelet
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deletactivity)
        supportActionBar?.title = "Trash"
        dbdelet = databasedelet(this)
        adepter2(dbdelet.getAllNotes(),this).also { adapter = it }
        findViewById<RecyclerView>(R.id.recleview).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recleview).adapter = adapter


    }


}