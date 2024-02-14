package com.example.githubuserapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubuserapp.data.local.entity.Favourite

@Database(entities = [Favourite::class], version = 1)
abstract class FavouriteDatabase: RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao
    companion object {
        @Volatile
        private var INSTANCE: FavouriteDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavouriteDatabase {
            if (INSTANCE == null) {
                synchronized(FavouriteDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavouriteDatabase::class.java, "favourite_database")
                        .build()
                }
            }
            return INSTANCE as FavouriteDatabase
        }
    }
}