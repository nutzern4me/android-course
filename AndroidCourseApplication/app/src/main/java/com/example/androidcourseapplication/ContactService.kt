package com.example.androidcourseapplication

import android.app.Service
import android.content.Intent
import android.nfc.Tag
import android.os.Binder
import android.os.IBinder
import android.util.Log

class ContactService : Service() {

    private val binder = ContactBinder()

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("ContactService","onBind")
        return binder
    }

    private fun getContactList(): List<Contact> {
        //Thread.sleep(5000)
        return ContactStorage.getContactList()
    }

    fun getContactDetails(contactId: Int): Contact? {
        //Thread.sleep(5000)
        return ContactStorage.getContactList().find { contact -> contact.id == contactId }
    }

    inner class ContactBinder : Binder() {
        fun getService(): ContactService = this@ContactService

        fun getContactList(): List<Contact> = this@ContactService.getContactList()

        fun getContactDetails(contactId: Int): Contact? =
            this@ContactService.getContactDetails(contactId)
    }
}