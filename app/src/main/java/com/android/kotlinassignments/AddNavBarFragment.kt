package com.android.kotlinassignments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.kotlinassignments.model.Contact


class AddNavBarFragment : Fragment() {
    private val adapter = ContactAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_nav_bar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameEditText: EditText = view.findViewById(R.id.editUserName)
        val emailEditText: EditText = view.findViewById(R.id.editUserEmail)
        val phoneEditText: EditText = view.findViewById(R.id.editUserPhone)
        val addButton: Button = view.findViewById(R.id.enterUserMB)

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()


            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()) {
                Toast.makeText(context, "User added!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(requireContext(), AddedContactsActivity::class.java))
                val contact = Contact(name = name, email = email, phone = phone)
                adapter.addContact(contact)

            } else {
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }

        }

    }
}