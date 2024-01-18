package com.example.githubuserapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.adapter.SearchAdapter
import com.example.githubuserapp.databinding.FragmentSearchBinding
import com.example.githubuserapp.response.ItemsItem
import com.example.githubuserapp.response.SearchResponse
import com.example.githubuserapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private val list = ArrayList<ItemsItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory())[SearchViewModel::class.java]


        mainViewModel.dataPerson.observe(viewLifecycleOwner){
            setData(it)
        }

        mainViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        mainViewModel.isSearching.observe(viewLifecycleOwner) {
            showNestedScrollView(it)
        }

        // Search Bar / Search View Action
        with(binding){
            searchBar.inflateMenu(R.menu.search_bar_menu)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    mainViewModel.setDataPerson(textView.text.toString())
                    mainViewModel.setSearchingState(true)
                    searchView.hide()
                    false
                }
        }

        // RecyclerView
        binding.rvPersonList.hasFixedSize()
        binding.rvPersonList.layoutManager = LinearLayoutManager(requireActivity())
    }


    private fun setData(person : List<ItemsItem>) {
        list.clear()
        list.addAll(person)
        val adapter = SearchAdapter(list)
        binding.rvPersonList.adapter = adapter
        for (item in list) {
            Log.d("API_RESPONSE", "Item Name: ${item.login}, Item Name: ${item.id}")
        }
    }

    private fun showLoading(isLoading : Boolean) {
        Log.d("SearchFragment", "showLoading: $isLoading")
        binding.pbSearchPerson.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showNestedScrollView(isSearching: Boolean) {
        if (isSearching){
            binding.nsvContent.visibility = View.VISIBLE
            binding.incLayoutSearchPerson.constraint.visibility = View.GONE
        } else {
            binding.nsvContent.visibility = View.INVISIBLE
            binding.incLayoutSearchPerson.constraint.visibility = View.VISIBLE
        }
    }
}