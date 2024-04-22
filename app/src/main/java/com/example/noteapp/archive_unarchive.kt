package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class archive_unarchive : AppCompatActivity() {


    private lateinit var dbarchive : datasearchive
    private var noteid: Int= -1
    private  var db:databasehhelper = databasehhelper(this)
    private var dbdelet : databasedelet = databasedelet(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archive_unarchive)

        dbarchive = datasearchive(this)
        noteid = intent.getIntExtra("item_id",-1)
        if(noteid == -1){
            finish()
            return
        }
        val note:addNote = dbarchive.getnote(noteid)
        findViewById<TextView>(R.id.ar_title5).setText(note.TITLE)
        findViewById<TextView>(R.id.ar_contant5).setText(note.CONTENT)



        findViewById<TextView>(R.id.ar_delete).setOnClickListener{
            val title = note.TITLE
            val context =note.CONTENT
            val date = note.DATE
            val id = note.ID
            val color = note.COLOUR
            val notee: addNote = addNote(id,title,context,date,color)

            dbdelet.insertnote(notee)
            dbarchive.deletenote(note.ID)
            val intent = Intent(this,archive::class.java)
            startActivity(intent)
        }
        findViewById<TextView>(R.id.ar_unarchive).setOnClickListener{
            val title = note.TITLE
            val context = note.CONTENT
            val date = note.DATE
            val id = note.ID
            val color = note.COLOUR
            val notee: addNote = addNote(id,title,context,date,color)

            db.insertnote(notee)
            dbarchive.deletenote(note.ID)
            val intent = Intent(this,archive::class.java)
            startActivity(intent)
        }


        val color = note.COLOUR
        when(color){
            1->{findViewById<TextView>(R.id.ar_contant5).setBackgroundResource(R.drawable.shape1)
                findViewById<TextView>(R.id.ar_title5).setBackgroundResource(R.drawable.shape1)}
            2-> {findViewById<TextView>(R.id.ar_contant5).setBackgroundResource(R.drawable.shap2)
                findViewById<TextView>(R.id.ar_title5).setBackgroundResource(R.drawable.shap2)}
            3->{findViewById<TextView>(R.id.ar_contant5).setBackgroundResource(R.drawable.shappink)
                findViewById<TextView>(R.id.ar_title5).setBackgroundResource(R.drawable.shappink)}
            4->{findViewById<TextView>(R.id.ar_contant5).setBackgroundResource(R.drawable.shapbrouwn)
                findViewById<TextView>(R.id.ar_title5).setBackgroundResource(R.drawable.shapbrouwn)}
            5->{findViewById<TextView>(R.id.ar_contant5).setBackgroundResource(R.drawable.shaplime)
                findViewById<TextView>(R.id.ar_title5).setBackgroundResource(R.drawable.shaplime)}
            6-> {
                findViewById<TextView>(R.id.ar_contant5).setBackgroundResource(R.drawable.shappurple)
                findViewById<TextView>(R.id.ar_title5).setBackgroundResource(R.drawable.shappurple)
            }
        }

    }

}