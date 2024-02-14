package com.example.githubuserapp.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserapp.ui.detail.add.AddFavouriteViewModel
import com.example.githubuserapp.ui.favourite.FavouriteViewModel

@Suppress("UNCHECKED_CAST")
class FavouriteViewModelFactory private constructor(private val mapplication: Application):
    ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddFavouriteViewModel::class.java)) {
            return AddFavouriteViewModel(mapplication) as T
        } else if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            return FavouriteViewModel(mapplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE: FavouriteViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): FavouriteViewModelFactory {
            if (INSTANCE == null) {
                synchronized(MainViewModelFactory::class.java) {
                    INSTANCE = FavouriteViewModelFactory(application)
                }
            }
            return INSTANCE as FavouriteViewModelFactory
        }
    }
}