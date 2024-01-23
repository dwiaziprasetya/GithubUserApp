package com.example.githubuserapp.ui.followingFollower.followers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FollowersViewModelFactory(private val username: String,
    private val index: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FollowersViewModel::class.java)) {
            return FollowersViewModel(username, index) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}