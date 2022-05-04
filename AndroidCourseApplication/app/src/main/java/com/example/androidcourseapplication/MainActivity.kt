package com.example.androidcourseapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder

class MainActivity : AppCompatActivity(), IContactService {
    private var contactService: ContactService? = null
    private var bound: Boolean = false

    override fun getService() = contactService

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as ContactService.ContactBinder
            contactService = binder.getService()
            bound = true
            replaceContactList()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, ContactService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

        if (bound) {
            unbindService(connection)
            bound = false
        }

        if (savedInstanceState == null) {
            replaceContactList()
        }
    }

    override fun onDestroy() {
        if (bound) {
            unbindService(connection)
            bound = false
        }
        super.onDestroy()
    }

    fun replaceContactList() {
        val contactListFragment = ContactListFragment.newInstance()

        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .replace(R.id.fragment_container, contactListFragment)
            .addToBackStack("ContactList")
            .commit()
    }
}