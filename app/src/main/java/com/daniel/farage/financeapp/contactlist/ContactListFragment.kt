package com.daniel.farage.financeapp.contactlist

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daniel.farage.financeapp.R
import com.daniel.farage.financeapp.data.model.Contact
import com.daniel.farage.financeapp.domain.states.ScreenState
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactListFragment : Fragment(R.layout.fragment_client_list) {

    lateinit var recyclerViewContacts: RecyclerView
    lateinit var loading: ProgressBar
    lateinit var searchViewContacts: SearchView

    private val contactsListAdapter: ContactsListAdapter by lazy { ContactsListAdapter() }
    private val contactListViewModel: ContactListViewModel by viewModel()
    private val contactList: MutableList<Contact> = emptyList<Contact>().toMutableList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        observer()
        setupRecyclerView()
        setupSearchView()
    }

    private fun setupViews(view: View) = with(view) {
        recyclerViewContacts = findViewById(R.id.recyclerViewContactsList)
        loading = findViewById(R.id.progress_circular)
        searchViewContacts = findViewById(R.id.searchView_contatos)

    }

    private fun observer() = lifecycleScope.launchWhenCreated {
        contactListViewModel.contactsListState.collect {
            when (it) {
                is ScreenState.Success -> {
                    contactsListAdapter.setContactList(it.data.data)
                    contactList.addAll(it.data.data)
                }
                is ScreenState.Error -> {
                }
                is ScreenState.Loading -> {
                    loading.isVisible = it.isLoading
                }
            }
        }
    }

    private fun setupSearchView() {
        searchViewContacts.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {
                    contactsListAdapter.setContactList(contactList.filter {
                        it.name.contains(newText, true)
                    })
                }

                return true
            }

        })
    }


    private fun setupRecyclerView() {
        recyclerViewContacts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = contactsListAdapter
        }
    }

}