package com.daniel.farage.financeapp.contactlist

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
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

    private val contactListViewModel: ContactListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        observer()
    }

    private fun setupViews(view: View) = with(view) {
        recyclerViewContacts = findViewById(R.id.recyclerViewContactsList)
        loading = findViewById(R.id.progress_circular)

    }

    private fun observer() = lifecycleScope.launchWhenCreated {
        contactListViewModel.contactsListState.collect {
            when(it) {
                is ScreenState.Success -> setupRecyclerView(it.data.data)
                is ScreenState.Error -> { }
                is ScreenState.Loading -> { loading.isVisible = it.isLoading }
            }
        }
    }


    private fun setupRecyclerView(contactsList: List<Contact>) {
        recyclerViewContacts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ContactsListAdapter(contactsList)
        }
    }

}