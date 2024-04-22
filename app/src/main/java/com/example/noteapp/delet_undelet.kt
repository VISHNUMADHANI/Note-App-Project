package com.example.noteapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class delet_undelet : AppCompatActivity() {

    private lateinit var dbdelet : databasedelet
    private var db :databasehhelper =  databasehhelper(this)
    private var noteid:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delet_undelet)
        dbdelet = databasedelet(this)
        noteid = intent.getIntExtra("item_id",-1)
        if(noteid == -1){
            finish()
            return
        }
        val note:addNote= dbdelet.getnote(noteid)
        findViewById<TextView>(R.id.de_title5).setText(note.TITLE)
        findViewById<TextView>(R.id.de_contant5).setText(note.CONTENT)

        var color:Int = note.COLOUR
        when(color){
            1->{findViewById<TextView>(R.id.de_contant5).setBackgroundResource(R.drawable.shape1)
                findViewById<TextView>(R.id.de_title5).setBackgroundResource(R.drawable.shape1)}
            2-> {findViewById<TextView>(R.id.de_contant5).setBackgroundResource(R.drawable.shap2)
                findViewById<TextView>(R.id.de_title5).setBackgroundResource(R.drawable.shap2)}
            3->{findViewById<TextView>(R.id.de_contant5).setBackgroundResource(R.drawable.shappink)
                findViewById<TextView>(R.id.de_title5).setBackgroundResource(R.drawable.shappink)}
            4->{findViewById<TextView>(R.id.de_contant5).setBackgroundResource(R.drawable.shapbrouwn)
                findViewById<TextView>(R.id.de_title5).setBackgroundResource(R.drawable.shapbrouwn)}
            5->{findViewById<TextView>(R.id.de_contant5).setBackgroundResource(R.drawable.shaplime)
                findViewById<TextView>(R.id.de_title5).setBackgroundResource(R.drawable.shaplime)}
            6->{findViewById<TextView>(R.id.de_contant5).setBackgroundResource(R.drawable.shappurple)
                findViewById<TextView>(R.id.de_title5).setBackgroundResource(R.drawable.shappurple)
            }



        }
        findViewById<TextView>(R.id.de_delete).setOnClickListener{
            dbdelet.deletenote(note.ID)
            val intent = Intent(this,Deletactivity::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.de_restor).setOnClickListener{

            val title = note.TITLE
            val content = note.CONTENT
            val color= note.COLOUR
            val date = note.DATE
            val id = note.ID
            val note : addNote = addNote(id,title,content,date,color)
            db.insertnote(note)
            dbdelet.deletenote(note.ID)
            val intent = Intent(this,Deletactivity::class.java)
            startActivity(intent)
        }


    }
}