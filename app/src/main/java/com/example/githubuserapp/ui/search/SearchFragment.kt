package com.example.githubuserapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
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
        with(binding){
            searchBar.inflateMenu(R.menu.search_bar_menu)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    setDataPerson(textView.text.toString())
                    searchView.hide()
                    false
                }
        }


        binding.rvPersonList.hasFixedSize()
        binding.rvPersonList.layoutManager = LinearLayoutManager(requireActivity())

//        binding.searchPersonTemplate.constraint.visibility = View.INVISIBLE
    }

    private fun setDataPerson(query : String) {
        val client = ApiConfig.getApiService().getSearch(query)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val responseBody = response.body()
                if (responseBody != null) {
                    list.clear()
                    setData(responseBody.items)
                    val adapter = SearchAdapter(list)
                    binding.rvPersonList.adapter = adapter
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun setData(person : List<ItemsItem>) {
        list.addAll(person)
        for (item in list) {
            Log.d("API_RESPONSE", "Item Name: ${item.login}, Item Name: ${item.id}")
            // Anda bisa menambahkan informasi lainnya yang ingin Anda lihat pada log
        }
    }
}