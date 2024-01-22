package com.example.githubuserapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.response.DetailResponse
import com.example.githubuserapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(username: String) : ViewModel() {
    private val _dataPerson = MutableLiveData<DetailResponse>()
    val dataPerson: LiveData<DetailResponse> = _dataPerson

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSetting = MutableLiveData<Boolean>()
    val isSetting: LiveData<Boolean> = _isSetting

    init {
        setPersonData(username)
    }

    private fun setPersonData(username:String) {
        _isSetting.value = false
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailPerson(username)
        Log.d("NAMA USERNAME WOYY", "showusername: ")
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _dataPerson.value = response.body()
                }
                _isSetting.value = true
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }
}