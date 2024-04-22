package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class archive : AppCompatActivity()  {
    private lateinit var noteadeptrerarcive : adepterarchive2
    private lateinit var db : databasehhelper
    private lateinit var dbarchive : datasearchive

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive)
        supportActionBar?.title= "Archive"
        dbarchive = datasearchive(this)
        db = databasehhelper(this)
        noteadeptrerarcive = adepterarchive2(dbarchive.getAllNotes(),this)
        findViewById<RecyclerView>(R.id.recleview).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recleview).adapter = noteadeptrerarcive


    }
}