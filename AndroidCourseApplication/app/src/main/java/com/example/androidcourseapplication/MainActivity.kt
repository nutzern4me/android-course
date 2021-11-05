package com.example.androidcourseapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contact = Contact(1, R.drawable.patric, "Иван", "88005553535",
            "4242", "ivan@mail.ru", "blazerboy69@mail.ru", "ровный чел")

        val contactList = listOf(contact)

        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .add(R.id.fragment_container, ContactListFragment(contactList))
            .addToBackStack("ContactList")
            .commit()
    }
}