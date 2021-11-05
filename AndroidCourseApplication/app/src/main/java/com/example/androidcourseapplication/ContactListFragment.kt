package com.example.androidcourseapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar

class ContactListFragment(_contactList : List<Contact>) : Fragment() {

    private val contactList = _contactList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_contact_details, container, false)

        layout.findViewById<ImageView>(R.id.contact_photo).setImageResource(contactList[0].photo)
        layout.findViewById<TextView>(R.id.contact_name).text = contactList[0].name
        layout.findViewById<TextView>(R.id.contact_phone).text = contactList[0].phoneNum
        layout.findViewById<LinearLayout>(R.id.contact_card).setOnClickListener{ openDetails() }

        activity?.findViewById<Toolbar>(R.id.toolbar)?.setTitle("Список контактов")

        return layout
    }

    private fun openDetails(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if (transaction!=null)
            transaction
                .replace(R.id.fragment_container, ContactDetailsFragment(contactList[0]))
                .addToBackStack("ContactDetails")
                .commit()
    }
}