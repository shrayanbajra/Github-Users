package com.example.githubusers.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubusers.R

class NavHostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

}