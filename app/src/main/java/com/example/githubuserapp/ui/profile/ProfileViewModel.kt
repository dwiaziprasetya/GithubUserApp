package com.example.githubuserapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.response.DetailResponse
import com.example.githubuserapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    private val _dataProfilePerson = MutableLiveData<DetailResponse>()
    val dataProfilePerson: LiveData<DetailResponse> = _dataProfilePerson

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSetting = MutableLiveData<Boolean>()
    val isSetting: LiveData<Boolean> = _isSetting

    companion object {
        const val USER = "dwiaziprasetya"
    }

    init {
        setProfileData()
    }

    private fun setProfileData() {
        _isSetting.value = false
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailPerson(USER)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _dataProfilePerson.value = response.body()
                }
                _isSetting.value = true
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _isLoading.value = false
            }
        })
    }
}