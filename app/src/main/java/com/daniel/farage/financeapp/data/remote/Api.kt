package com.daniel.farage.financeapp.data.remote

import com.daniel.farage.financeapp.data.model.Contact
import retrofit2.http.GET

interface Api {

    @GET("users/")
    suspend fun getAllContactsList() : List<Contact>

}