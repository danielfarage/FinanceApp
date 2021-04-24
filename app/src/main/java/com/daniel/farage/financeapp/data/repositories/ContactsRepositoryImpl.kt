package com.daniel.farage.financeapp.data.repositories

import com.daniel.farage.financeapp.data.model.Contact
import com.daniel.farage.financeapp.data.model.ContactsListResponse
import com.daniel.farage.financeapp.data.remote.Api
import com.daniel.farage.financeapp.domain.repositories.ContactsRepository
import com.daniel.farage.financeapp.domain.states.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class ContactsRepositoryImpl(private val api: Api) : ContactsRepository {

    override fun loadContactsListRemote(): Flow<DataState<ContactsListResponse>> = flow {
        try {
            val listContact = api.getAllContactsList().sortedBy { it.name }
            emit(DataState.Success(ContactsListResponse(listContact)))
        } catch (error: Exception) {
            emit(DataState.Failure<ContactsListResponse>(error))
        }
    }.flowOn(Dispatchers.IO)

}