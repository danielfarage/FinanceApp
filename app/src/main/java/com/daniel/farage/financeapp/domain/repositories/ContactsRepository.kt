package com.daniel.farage.financeapp.domain.repositories

import com.daniel.farage.financeapp.data.model.ContactsListResponse
import com.daniel.farage.financeapp.domain.states.DataState
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {

    fun loadContactsListRemote() : Flow<DataState<ContactsListResponse>>

}