package com.example.githubuserapp.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserapp.ui.followingFollower.followItems.FollowItemsViewModel

@Suppress("UNCHECKED_CAST")
class FollowItemsViewModelFactory(private val username: String,
                                  private val index: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FollowItemsViewModel::class.java)) {
            return FollowItemsViewModel(username, index) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}