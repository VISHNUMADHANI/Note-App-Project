package com.example.noteapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class adddnote : AppCompatActivity() {
    private val db : databasehhelper = databasehhelper(this)
    private var color:Int=1
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_adddnote2)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<ImageView>(R.id.bgblue).setOnClickListener{
            findViewById<EditText>(R.id.contant5).setBackgroundResource(R.drawable.shap2)
            findViewById<EditText>(R.id.title5).setBackgroundResource(R.drawable.shap2)
            color = 2
        }
        findViewById<ImageView>(R.id.bgpink).setOnClickListener{
            findViewById<EditText>(R.id.contant5).setBackgroundResource(R.drawable.shappink)
            findViewById<EditText>(R.id.title5).setBackgroundResource(R.drawable.shappink)
            color = 3
        }
        findViewById<ImageView>(R.id.bgcream).setOnClickListener{
            findViewById<EditText>(R.id.contant5).setBackgroundResource(R.drawable.shape1)
            findViewById<EditText>(R.id.title5).setBackgroundResource(R.drawable.shape1)
            color = 1
        }
        findViewById<ImageView>(R.id.bgbrown).setOnClickListener{
            findViewById<EditText>(R.id.contant5).setBackgroundResource(R.drawable.shapbrouwn)
            findViewById<EditText>(R.id.title5).setBackgroundResource(R.drawable.shapbrouwn)
            color = 4
        }


        findViewById<ImageView>(R.id.bglime).setOnClickListener{
            findViewById<EditText>(R.id.contant5).setBackgroundResource(R.drawable.shaplime)
            findViewById<EditText>(R.id.title5).setBackgroundResource(R.drawable.shaplime)
            color = 5
        }
        findViewById<ImageView>(R.id.bgpurpul).setOnClickListener{
            findViewById<EditText>(R.id.contant5).setBackgroundResource(R.drawable.shappurple)
            findViewById<EditText>(R.id.title5).setBackgroundResource(R.drawable.shappurple)
            color = 6
        }


        findViewById<TextView>(R.id.save4).setOnClickListener{
            val Title = findViewById<EditText>(R.id.title5).text.toString()
            val Context = findViewById<EditText>(R.id.contant5).text.toString()
            val date = db.getcurrentdate()
            val colour = color
            val insert = addNote(0,Title,Context,date,colour)
            db.insertnote(insert)
            finish()
        }
    }
}