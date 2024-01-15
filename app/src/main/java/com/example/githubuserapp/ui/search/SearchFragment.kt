package com.example.githubuserapp.ui.search

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            searchBar.inflateMenu(R.menu.search_bar_menu)
        }

//        binding.searchPersonTemplate.constraint.visibility = View.INVISIBLE
    }
}