package com.example.androidcourseapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .add(R.id.fragment_container, ContactListFragment())
            .addToBackStack("ContactList")
            .commit()
    }
}