package com.daniel.farage.financeapp.cardlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.daniel.farage.financeapp.R

class CardListFragment: Fragment(R.layout.fragment_card_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = view.findViewById<AppCompatButton>(R.id.btn_addCard)
        btn.setOnClickListener { navga() }
    }

    private fun navga() {
        findNavController().navigate(R.id.action_cardListFragment_to_addCardFragment)
    }

}