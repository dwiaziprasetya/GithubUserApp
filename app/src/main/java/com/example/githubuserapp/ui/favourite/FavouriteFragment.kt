package com.example.githubuserapp.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.helper.FavouriteViewModelFactory
import com.example.githubuserapp.databinding.FragmentFavouriteBinding
import com.example.githubuserapp.ui.adapter.FavouriteAdapter

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavouriteAdapter()

        val favouriteViewModel = obtainViewModel(this@FavouriteFragment)
        favouriteViewModel.getAllFavourites().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setListFavourite(it)
            }
        }

        binding.rvFavourite.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFavourite.hasFixedSize()
        binding.rvFavourite.adapter = adapter
        binding.toolbarFavourite.setNavigationOnClickListener { handleNavigationClick() }
    }

    private fun obtainViewModel(fragment: Fragment): FavouriteViewModel{
        val factory = FavouriteViewModelFactory.getInstance(fragment.requireActivity().application)
        return ViewModelProvider(fragment, factory)[FavouriteViewModel::class.java]
    }

    private fun handleNavigationClick(){
        findNavController().popBackStack()
    }
}