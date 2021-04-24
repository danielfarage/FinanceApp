package com.daniel.farage.financeapp.contactlist

import com.daniel.farage.financeapp.data.model.ContactsListResponse
import com.daniel.farage.financeapp.data.remote.Api
import com.daniel.farage.financeapp.domain.repositories.ContactsRepository
import com.daniel.farage.financeapp.domain.states.DataState
import com.daniel.farage.financeapp.domain.usecases.GetContactsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class ContactListInterector(private val repository: ContactsRepository): GetContactsListUseCase {

    override fun execute(): Flow<DataState<ContactsListResponse>> = repository.loadContactsListRemote()
}