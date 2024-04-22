package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class privacy_policy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
        supportActionBar?.title = "Privacy-Policy"
    }
}