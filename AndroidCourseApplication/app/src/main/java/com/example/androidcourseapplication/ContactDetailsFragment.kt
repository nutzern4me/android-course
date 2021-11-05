package com.example.androidcourseapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar

class ContactDetailsFragment(_contact : Contact) : Fragment() {

    private val contact = _contact

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_contact_details, container, false)

        layout.findViewById<ImageView>(R.id.details_photo).setImageResource(contact.photo)
        layout.findViewById<TextView>(R.id.details_name).text = contact.name
        layout.findViewById<TextView>(R.id.details_phone).text = contact.phoneNum
        layout.findViewById<TextView>(R.id.details_phone2).text = contact.phoneNum2
        layout.findViewById<TextView>(R.id.details_email).text = contact.email
        layout.findViewById<TextView>(R.id.details_email2).text = contact.email2
        layout.findViewById<TextView>(R.id.details_description).text = contact.description

        activity?.findViewById<Toolbar>(R.id.toolbar)?.setTitle("Детали контакта")

        return layout
    }
}