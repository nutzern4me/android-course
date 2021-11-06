package com.example.androidcourseapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar

class ContactDetailsFragment() : Fragment(R.layout.fragment_contact_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contact =
            MainActivity.ContactStorage.contacts.find { contact -> contact.id == arguments?.get("ContactId") }

        if (contact != null) {
            view.findViewById<ImageView>(R.id.details_photo).setImageResource(contact.photo)
            view.findViewById<TextView>(R.id.details_name).text = contact.name
            view.findViewById<TextView>(R.id.details_phone).text = contact.phoneNum
            view.findViewById<TextView>(R.id.details_phone2).text = contact.phoneNum2
            view.findViewById<TextView>(R.id.details_email).text = contact.email
            view.findViewById<TextView>(R.id.details_email2).text = contact.email2
            view.findViewById<TextView>(R.id.details_description).text = contact.description
        }

        activity?.findViewById<Toolbar>(R.id.toolbar)?.setTitle("Детали контакта")
    }
}