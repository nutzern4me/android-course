package com.example.androidcourseapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*

class ContactListFragment() : Fragment(R.layout.fragment_contact_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contact = MainActivity.ContactStorage.contacts[0]

        view.findViewById<ImageView>(R.id.contact_photo).setImageResource(contact.photo)
        view.findViewById<TextView>(R.id.contact_name).text = contact.name
        view.findViewById<TextView>(R.id.contact_phone).text = contact.phoneNum
        view.findViewById<LinearLayout>(R.id.contact_card)
            .setOnClickListener { openDetails(contact.id) }

        activity?.findViewById<Toolbar>(R.id.toolbar)?.setTitle("Список контактов")
    }

    private fun openDetails(contactId: Int) {
        val detailsFragment = ContactDetailsFragment()

        val args = Bundle()
        args.putInt("ContactId", contactId)
        detailsFragment.arguments = args

        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if (transaction != null)
            transaction
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack("ContactDetails")
                .commit()
    }
}