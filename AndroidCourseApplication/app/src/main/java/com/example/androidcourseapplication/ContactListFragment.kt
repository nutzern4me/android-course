package com.example.androidcourseapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout

class ContactListFragment() : Fragment(R.layout.fragment_contact_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val contact = ContactStorage.getContactList()[0]

        view.findViewById<ImageView>(R.id.contact_photo).setImageResource(contact.photo)
        view.findViewById<TextView>(R.id.contact_name).text = contact.name
        view.findViewById<TextView>(R.id.contact_phone).text = contact.phoneNums[0]
        view.findViewById<LinearLayout>(R.id.contact_card)
            .setOnClickListener { openDetails(contact.id) }

        requireActivity().findViewById<Toolbar>(R.id.toolbar)?.setTitle(R.string.contact_list)
    }

    private fun openDetails(contactId: Int) {
        val detailsFragment = ContactDetailsFragment.newInstance(contactId)

        val transaction = requireActivity().supportFragmentManager?.beginTransaction()
        if (transaction != null)
            transaction
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack("ContactDetails")
                .commit()
    }
}