package com.android.kotlinassignments
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.android.kotlinassignments.databinding.ItemContactBinding
import com.android.kotlinassignments.model.Contact

class ContactAdapter: Adapter<ContactAdapter.ContactViewHolder>() {
    private var contactList = mutableListOf<Contact>()

//    var onDeleteClick:OnDeleteClick ?=null
    var onDeleteClick:((Int)->Unit)? = null

    fun setContactList(list: MutableList<Contact>){
        contactList = list
    }
    fun isEmpty() = contactList.isEmpty()
    fun deleteContact(position: Int){
        contactList.removeAt(position)
        notifyItemRemoved(position)
    }
    fun addContact(contact: Contact){
        contactList.add(contact)
        notifyItemInserted(contactList.size-1)
    }
    class ContactViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact){
            binding.userName.text = contact.name
            binding.emailText.text = contact.email
            binding.phoneText.text = contact.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context))
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList [position]
        holder.bind(contact)

        holder.binding.deleteButton.setOnClickListener {
            onDeleteClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
       return contactList.size
    }
    fun getContactList(): List<Contact> = contactList

    fun interface OnDeleteClick{
        fun onDelete(position: Int)
    }
}


