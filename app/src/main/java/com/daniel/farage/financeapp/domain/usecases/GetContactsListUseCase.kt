package com.daniel.farage.financeapp.domain.usecases

import com.daniel.farage.financeapp.data.model.ContactsListResponse
import com.daniel.farage.financeapp.domain.states.DataState
import kotlinx.coroutines.flow.Flow

interface GetContactsListUseCase {

    fun execute(): Flow<DataState<ContactsListResponse>>

}