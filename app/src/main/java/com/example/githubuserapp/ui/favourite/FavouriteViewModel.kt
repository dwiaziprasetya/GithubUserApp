package com.example.githubuserapp.ui.favourite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.data.local.FavouriteRepository
import com.example.githubuserapp.data.local.entity.Favourite

class FavouriteViewModel(application: Application) : ViewModel() {
    private val mFavouriteRepository: FavouriteRepository = FavouriteRepository(application)

    fun getAllFavourites(): LiveData<List<Favourite>> = mFavouriteRepository.getAllFavourites()
}