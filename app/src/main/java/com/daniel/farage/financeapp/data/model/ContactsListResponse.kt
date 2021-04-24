package com.daniel.farage.financeapp.data.model

data class ContactsListResponse(
        val data: List<Contact>
)

data class Contact(val id: Int,
                   val name: String,
                   val img: String,
                   val username: String)
