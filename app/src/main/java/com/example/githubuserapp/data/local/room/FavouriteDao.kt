package com.example.githubuserapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubuserapp.data.local.entity.Favourite

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favourite: Favourite)

    @Delete
    fun delete(favourite: Favourite)

    @Query("SELECT * from favourite ORDER BY username ASC")
    fun getAllFavourites(): LiveData<List<Favourite>>

    @Query("SELECT COUNT(*) from favourite WHERE username = :username")
    fun isUserFavourite(username: String): LiveData<Boolean>
}