package com.example.noteapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class update : AppCompatActivity() {

    private lateinit var  db : databasehhelper
    private  var noteid:Int = -1
    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        db = databasehhelper(this)
        noteid = intent.getIntExtra("item_id",-1)
        if(noteid == -1){
            finish()
            return
        }
        val note:addNote= db.getnote(noteid)
            findViewById<EditText>(R.id.up_title5).setText(note.TITLE)
            findViewById<EditText>(R.id.up_contant5).setText(note.CONTENT)
        var color:Int = note.COLOUR
        when(color){
            1->{findViewById<EditText>(R.id.up_contant5).setBackgroundResource(R.drawable.shape1)
                findViewById<EditText>(R.id.up_title5).setBackgroundResource(R.drawable.shape1)}
            2-> {findViewById<EditText>(R.id.up_contant5).setBackgroundResource(R.drawable.shap2)
            findViewById<EditText>(R.id.up_title5).setBackgroundResource(R.drawable.shap2)}
            3->{findViewById<EditText>(R.id.up_contant5).setBackgroundResource(R.drawable.shappink)
                findViewById<EditText>(R.id.up_title5).setBackgroundResource(R.drawable.shappink)}
            4->{findViewById<EditText>(R.id.up_contant5).setBackgroundResource(R.drawable.shapbrouwn)
                findViewById<EditText>(R.id.up_title5).setBackgroundResource(R.drawable.shapbrouwn)}
            5->{findViewById<EditText>(R.id.up_contant5).setBackgroundResource(R.drawable.shaplime)
                findViewById<EditText>(R.id.up_title5).setBackgroundResource(R.drawable.shaplime)}
            6->{findViewById<EditText>(R.id.up_contant5).setBackgroundResource(R.drawable.shappurple)
                findViewById<EditText>(R.id.up_title5).setBackgroundResource(R.drawable.shappurple)
                }

        }

        findViewById<ImageView>(R.id.bgblue).setOnClickListener{
            findViewById<EditText>(R.id.up_contant5).setBackgroundResource(R.drawable.shap2)
            findViewById<EditText>(R.id.up_title5).setBackgroundResource(R.drawable.shap2)
            color = 2
        }
        findViewById<ImageView>(R.id.bgpink).setOnClickListener{
            findViewById<EditText>(R.id.up_contant5).setBackgroundResource(R.drawable.shappink)
            findViewById<EditText>(R.id.up_title5).setBackgroundResource(R.drawable.shappink)
            color = 3
        }
        findViewById<ImageView>(R.id.bgcream).setOnClickListener{
            findViewById<EditText>(R.id.up_contant5).setBackgroundResource(R.drawable.shape1)
            findViewById<EditText>(R.id.up_title5).setBackgroundResource(R.drawable.shape1)
            color = 1
        }
        findViewById<ImageView>(R.id.bgbrown).setOnClickListener{
            findViewById<EditText>(R.id.up_contant5).setBackgroundResource(R.drawable.shapbrouwn)
            findViewById<EditText>(R.id.up_title5).setBackgroundResource(R.drawable.shapbrouwn)
            color = 4
        }


        findViewById<ImageView>(R.id.bglime).setOnClickListener{
            findViewById<EditText>(R.id.up_contant5).setBackgroundResource(R.drawable.shaplime)
            findViewById<EditText>(R.id.up_title5).setBackgroundResource(R.drawable.shaplime)
            color = 5
        }
        findViewById<ImageView>(R.id.bgpurpul).setOnClickListener{
            findViewById<EditText>(R.id.up_contant5).setBackgroundResource(R.drawable.shappurple)
            findViewById<EditText>(R.id.up_title5).setBackgroundResource(R.drawable.shappurple)
            color = 6
        }

        findViewById<TextView>(R.id.de_restor).setOnClickListener(){
            val Title = findViewById<EditText>(R.id.up_title5).text.toString()
            val Context = findViewById<EditText>(R.id.up_contant5).text.toString()
            val date = db.getcurrentdate()
            val update = addNote(noteid,Title,Context,date,color)
            db.uppdate(update)
            finish()
            Toast.makeText(this,"Change Saved",Toast.LENGTH_LONG).show()
//
        }





    }
}