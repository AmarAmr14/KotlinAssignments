package com.android.kotlinassignments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.kotlinassignments.databinding.ActivityAddedContactsBinding
import com.android.kotlinassignments.model.Contact

class AddedContactsActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    lateinit var binding:ActivityAddedContactsBinding
    private val adapter = ContactAdapter()
    private val contactList = mutableListOf<Contact>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddedContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initContactsRV()
    }


    private fun initContactsRV() {
        binding.contactsRecyclerView.adapter = adapter
        adapter.setContactList(contactList)

        adapter.onDeleteClick = {position->
            adapter.deleteContact(position)
        }
    }
}