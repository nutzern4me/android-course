package com.example.androidcourseapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val contactListFragment = ContactListFragment()

            val transaction = supportFragmentManager.beginTransaction()
            transaction
                .add(R.id.fragment_container, contactListFragment)
                .addToBackStack("ContactList")
                .commit()
        }
    }
}