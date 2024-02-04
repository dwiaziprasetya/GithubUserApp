package com.example.githubuserapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.response.ItemsItem
import com.example.githubuserapp.response.SearchResponse
import com.example.githubuserapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    private val _dataPerson = MutableLiveData<List<ItemsItem>>()
    val dataPerson: LiveData<List<ItemsItem>> = _dataPerson

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSearching = MutableLiveData<Boolean>()
    val isSearching: LiveData<Boolean> = _isSearching


    fun setSearchingState(isSearching: Boolean) {
        _isSearching.value = isSearching
    }

    fun setDataPerson(query : String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearch(query)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) _dataPerson.value = response.body()?.items
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }
}