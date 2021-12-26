package com.example.androidcourseapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout

class ContactListFragment() : Fragment(R.layout.fragment_contact_list) {

    private lateinit var contactService: ContactService
    private lateinit var contactBinder: ContactService.ContactBinder
    private var bound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            contactBinder = service as ContactService.ContactBinder
            contactService = contactBinder.getService()
            bound = true
            showContactList()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val intent = Intent(requireContext(), ContactService::class.java)
        requireActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(bound)
            showContactList()

        requireActivity().findViewById<Toolbar>(R.id.toolbar)?.setTitle(R.string.contact_list)
    }

    private fun showContactList(){
        val contact = contactBinder.getContactList()[0]

        view?.findViewById<ImageView>(R.id.contact_photo)?.setImageResource(contact.photo)
        view?.findViewById<TextView>(R.id.contact_name)?.text = contact.name
        view?.findViewById<TextView>(R.id.contact_phone)?.text = contact.phoneNums[0]
        view?.findViewById<LinearLayout>(R.id.contact_card)
            ?.setOnClickListener { openDetails(contact.id) }
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

    override fun onDestroyView() {
        if (bound) {
            requireActivity().unbindService(connection)
            bound = false
        }

        super.onDestroyView()
    }
}