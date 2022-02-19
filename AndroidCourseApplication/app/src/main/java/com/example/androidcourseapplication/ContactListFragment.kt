package com.example.androidcourseapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout
import java.lang.ref.WeakReference

class ContactListFragment : Fragment(R.layout.fragment_contact_list) {
    private var contactService: ContactService? = null

    companion object {
        fun newInstance() = ContactListFragment()
    }

    fun setData(contacts: List<Contact>) {
        val contact = contacts[0]

        requireActivity().runOnUiThread {
            requireView().findViewById<ImageView>(R.id.contact_photo)
                .setImageResource(contact.photo)
            requireView().findViewById<TextView>(R.id.contact_name).text = contact.name
            requireView().findViewById<TextView>(R.id.contact_phone).text = contact.phoneNums[0]
            requireView().findViewById<LinearLayout>(R.id.contact_card)
                .setOnClickListener { openDetails(contact.id) }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is IContactService) {
            contactService = context.getService()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contactService?.getContactList(WeakReference(this))

        requireActivity().findViewById<Toolbar>(R.id.toolbar)?.setTitle(R.string.contact_list)
    }

    private fun openDetails(contactId: Int) {
        val detailsFragment = ContactDetailsFragment.newInstance(contactId)

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction
            .replace(R.id.fragment_container, detailsFragment)
            .addToBackStack("ContactDetails")
            .commit()
    }

    override fun onDetach() {
        contactService = null
        super.onDetach()
    }
}