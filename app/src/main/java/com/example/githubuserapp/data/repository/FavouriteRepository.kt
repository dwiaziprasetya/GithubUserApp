package com.example.githubuserapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubuserapp.data.local.entity.Favourite
import com.example.githubuserapp.data.local.room.FavouriteDao
import com.example.githubuserapp.data.local.room.FavouriteDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavouriteRepository(application: Application) {
    private val mFavouriteDao: FavouriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavouriteDatabase.getDatabase(application)
        mFavouriteDao = db.favouriteDao()
    }

    fun getAllFavourites(): LiveData<List<Favourite>> = mFavouriteDao.getAllFavourites()
    fun isUserFavourite(username: String): LiveData<Boolean> = mFavouriteDao.isUserFavourite(username)

    fun insert(favourite: Favourite) {
        executorService.execute { mFavouriteDao.insert(favourite) }
    }

    fun delete(favourite: Favourite) {
        executorService.execute { mFavouriteDao.delete(favourite) }
    }
}