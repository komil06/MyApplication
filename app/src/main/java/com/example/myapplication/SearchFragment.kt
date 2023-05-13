package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentContactsBinding
import com.example.myapplication.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    var contacts_search = mutableListOf<Contact>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        val db = DBHelper(requireContext())

        contacts_search = db.getContacts()


            var adapter = ContactAdapter(contacts_search, object : ContactAdapter.ContactInterface{
                override fun onClick(contact: Contact) {
                    val bundle = bundleOf()
                    bundle.putSerializable("contact",contact)
                    findNavController().navigate(R.id.action_searchFragment_to_viewFragment,bundle)
                }

            })
           binding.searchRV.adapter = adapter

        binding.searchView.setOnSearchClickListener {
            val filter = mutableListOf<Contact>()
            if (it != null){
                for (c in contacts_search){
                    if (c.name.lowercase().contains(it.toString().lowercase())){
                        filter.add(c)
                    }
                }

            }
        }
        return binding.root
    }

}