package com.daniel.farage.financeapp.contactlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniel.farage.financeapp.data.model.ContactsListResponse
import com.daniel.farage.financeapp.domain.states.DataState
import com.daniel.farage.financeapp.domain.states.ScreenState
import com.daniel.farage.financeapp.domain.usecases.GetContactsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ContactListViewModel(
        private val useCaseContacts: GetContactsListUseCase
): ViewModel() {

    private val _contactsListState = MutableStateFlow<ScreenState<ContactsListResponse>>(ScreenState.Loading(true))
    val contactsListState = _contactsListState.asSharedFlow()

    init {
        retrieveContactsList()
    }

    private fun retrieveContactsList() = viewModelScope.launch {
        val result = useCaseContacts.execute().collect { result ->
            when(result) {
              is DataState.Success -> {
                  _contactsListState.emit(ScreenState.Loading(false))

                  _contactsListState.emit(ScreenState.Success(result.data))
              }
              is DataState.Failure -> { _contactsListState.emit(ScreenState.Error("Falha ao carregar lista")) }
            }
        }
    }

}