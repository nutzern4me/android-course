package com.example.androidcourseapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.os.bundleOf
import java.lang.ref.WeakReference

class ContactDetailsFragment : Fragment(R.layout.fragment_contact_details) {
    private var contactService: ContactService? = null

    companion object {
        const val CONTACT_ID_KEY = "ContactId"

        fun newInstance(contactId: Int) = ContactDetailsFragment().apply {
            arguments = bundleOf(CONTACT_ID_KEY to contactId)
        }
    }

    fun setData(contact: Contact?) {
        if (contact != null) {
            requireActivity().runOnUiThread {
                requireView().findViewById<ImageView>(R.id.details_photo)
                    .setImageResource(contact.photo)
                requireView().findViewById<TextView>(R.id.details_name).text = contact.name
                requireView().findViewById<TextView>(R.id.details_phone).text = contact.phoneNums[0]
                requireView().findViewById<TextView>(R.id.details_phone2).text =
                    contact.phoneNums[1]
                requireView().findViewById<TextView>(R.id.details_email).text = contact.emails[0]
                requireView().findViewById<TextView>(R.id.details_email2).text = contact.emails[1]
                requireView().findViewById<TextView>(R.id.details_description).text =
                    contact.description
            }
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

        val contactId = requireArguments().get(CONTACT_ID_KEY)
        contactService?.getContactDetails(contactId as Int, WeakReference(this))

        requireActivity().findViewById<Toolbar>(R.id.toolbar)?.setTitle(R.string.contact_details)
    }

    override fun onDetach() {
        contactService = null
        super.onDetach()
    }
}