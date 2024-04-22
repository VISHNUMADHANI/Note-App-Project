package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var db:databasehhelper
    private lateinit var noteadepter:adepter
    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.decorView.systemUiVisibility = (
//                View.SYSTEM_UI_FLAG_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                )
        setContentView(R.layout.activity_main)
        val dravablelay : DrawerLayout = findViewById(R.id.draw_layout)
        val navigation = findViewById<NavigationView>(R.id.navigation_main)
        toggle = ActionBarDrawerToggle(this,dravablelay,R.string.open,R.string.close)
        dravablelay.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigation.setNavigationItemSelectedListener {


            when (it.itemId) {


                R.id.trash -> { val intent = Intent(this,Deletactivity::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "Trash", Toast.LENGTH_SHORT).show()
                }
                R.id.setting -> Toast.makeText(applicationContext, "Setting", Toast.LENGTH_SHORT)
                    .show()

                R.id.Policies ->{val intent = Intent(this,privacy_policy::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "Privact-Policy", Toast.LENGTH_SHORT).show()
                }

                R.id.archive -> {
                    val intent = Intent(this,archive::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "Archivre", Toast.LENGTH_SHORT)
                        .show()

                }
            }

            true
        }

        db = databasehhelper(this)
        noteadepter = adepter(db.getAllNotes(),this)
        findViewById<RecyclerView>(R.id.recleview).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recleview).adapter = noteadepter


        findViewById<ImageView>(R.id.add).setOnClickListener() {
            val intent = Intent(this,adddnote::class.java)
            startActivity(intent)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onResume() {
        super.onResume()
        noteadepter.refreshdata(db.getAllNotes())
    }
}