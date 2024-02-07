package com.example.githubuserapp.ui.detail.add

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.data.local.FavouriteRepository
import com.example.githubuserapp.data.local.entity.Favourite

class AddFavouriteViewModel(application: Application): ViewModel() {
    private val mFavouriteRepository = FavouriteRepository(application)

    fun insert(favourite: Favourite){
        mFavouriteRepository.insert(favourite)
    }

    fun delete(favourite: Favourite){
        mFavouriteRepository.delete(favourite)
    }

    fun isUserFavourite(username: String): LiveData<Boolean> {
        return mFavouriteRepository.isUserFavourite(username)
    }
}