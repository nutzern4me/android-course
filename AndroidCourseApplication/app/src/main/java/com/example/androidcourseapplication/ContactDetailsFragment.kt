package com.example.androidcourseapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.os.bundleOf

class ContactDetailsFragment() : Fragment(R.layout.fragment_contact_details) {

    companion object {
        const val CONTACT_ID_KEY = "ContactId"

        fun newInstance(contactId: Int) = ContactDetailsFragment().apply {
            arguments = bundleOf(CONTACT_ID_KEY to contactId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contactId = requireArguments().get(CONTACT_ID_KEY)
        val contact =
            ContactStorage.getContactList().find { contact -> contact.id == contactId }

        if (contact != null) {
            view.findViewById<ImageView>(R.id.details_photo).setImageResource(contact.photo)
            view.findViewById<TextView>(R.id.details_name).text = contact.name
            view.findViewById<TextView>(R.id.details_phone).text = contact.phoneNums[0]
            view.findViewById<TextView>(R.id.details_phone2).text = contact.phoneNums[1]
            view.findViewById<TextView>(R.id.details_email).text = contact.emails[0]
            view.findViewById<TextView>(R.id.details_email2).text = contact.emails[1]
            view.findViewById<TextView>(R.id.details_description).text = contact.description
        }

        requireActivity().findViewById<Toolbar>(R.id.toolbar)?.setTitle(R.string.contact_details)
    }
}