package com.example.githubuserapp.ui.followingFollower.followers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.response.FollowersResponseItem
import com.example.githubuserapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel(username: String, index: Int): ViewModel() {
    private val _dataPerson = MutableLiveData<List<FollowersResponseItem>>()
    val dataPerson: LiveData<List<FollowersResponseItem>> = _dataPerson

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        setFollowData(username, index)
    }

    private fun setFollowData(username: String, index: Int) {
        _isLoading.value = true
        when(index) {
            0 -> { // Follower
                val clientFollower = ApiConfig.getApiService().getFollowers(username)
                clientFollower.enqueue(object: Callback<List<FollowersResponseItem>> {
                    override fun onResponse(
                        call: Call<List<FollowersResponseItem>>,
                        response: Response<List<FollowersResponseItem>>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            _dataPerson.value = response.body()
                            Log.d("Followers Fragment", "index : $index")
                        }
                    }

                    override fun onFailure(call: Call<List<FollowersResponseItem>>, t: Throwable) {
                        _isLoading.value = false
                    }
                })
            }
            1 -> { // Following
                val clientFollowing = ApiConfig.getApiService().getFollowing(username)
                clientFollowing.enqueue(object: Callback<List<FollowersResponseItem>> {
                    override fun onResponse(
                        call: Call<List<FollowersResponseItem>>,
                        response: Response<List<FollowersResponseItem>>
                    ) {
                        _isLoading.value = false
                        if (response.isSuccessful) {
                            Log.d("Followers Fragment", "index : $index")
                            _dataPerson.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<List<FollowersResponseItem>>, t: Throwable) {
                        _isLoading.value = false
                    }
                })
            }
        }
    }
}