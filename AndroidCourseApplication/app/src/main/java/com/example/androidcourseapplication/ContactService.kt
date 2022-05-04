package com.example.androidcourseapplication

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

class ContactService : Service() {

    private val binder = ContactBinder()

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    fun getContactList(contactListFragment: WeakReference<ContactListFragment>) {
        thread(start = true) {
            Thread.sleep(5000)
            contactListFragment.get()?.setData(ContactStorage.getContactList())
        }
    }

    fun getContactDetails(
        contactId: Int,
        contactDetailsFragment: WeakReference<ContactDetailsFragment>
    ) {
        thread(start = true) {
            Thread.sleep(5000)
            val contact =
                ContactStorage.getContactList().find { contact -> contact.id == contactId }
            contactDetailsFragment.get()?.setData(contact)
        }
    }

    inner class ContactBinder : Binder() {
        fun getService(): ContactService = this@ContactService
    }
}