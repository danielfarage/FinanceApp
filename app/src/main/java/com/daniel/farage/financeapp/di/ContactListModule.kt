package com.daniel.farage.financeapp.di

import com.daniel.farage.financeapp.contactlist.ContactListInterector
import com.daniel.farage.financeapp.contactlist.ContactListViewModel
import com.daniel.farage.financeapp.data.repositories.ContactsRepositoryImpl
import com.daniel.farage.financeapp.domain.repositories.ContactsRepository
import com.daniel.farage.financeapp.domain.usecases.GetContactsListUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ContactListModule {
    val contactListModule = module {
        viewModel { ContactListViewModel(get()) }
        factory<GetContactsListUseCase> { ContactListInterector(get()) }
        single<ContactsRepository> { ContactsRepositoryImpl(get()) }
    }
}