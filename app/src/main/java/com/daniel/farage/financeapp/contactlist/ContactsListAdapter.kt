package com.daniel.farage.financeapp.contactlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.daniel.farage.financeapp.R
import com.daniel.farage.financeapp.data.model.Contact
import com.daniel.farage.financeapp.data.model.ContactsListResponse

class ContactsListAdapter: RecyclerView.Adapter<ContactsListAdapter.ContactsListViewHolder>() {


    private val contactsList: MutableList<Contact> = emptyList<Contact>().toMutableList()

    class ContactsListViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        private val imageViewPhotoContact: ImageView = view.findViewById(R.id.imageView_contactPhoto)
        private val textViewContact: TextView = view.findViewById(R.id.textView_contact)
        private val textViewContactName: TextView = view.findViewById(R.id.textView_contactName)

        fun bind(contact: Contact) {
            textViewContact.text = contact.username
            textViewContactName.text = contact.name

            Glide.with(view.context)
                .load(contact.img)
                .centerCrop()
                .transform(RoundedCorners(120))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewPhotoContact)
        }
    }

    fun setContactList(list: List<Contact>) {
        contactsList.clear()
        contactsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListViewHolder {
        return ContactsListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_contatos,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactsListViewHolder, position: Int) {
        holder.bind(contactsList[position])
    }

    override fun getItemCount(): Int = contactsList.size
}