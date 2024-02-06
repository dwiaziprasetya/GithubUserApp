package com.example.githubuserapp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.githubuserapp.data.local.entity.Favourite

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favourite: Favourite)

    @Update
    fun update(favourite: Favourite)

    @Delete
    fun delete(favourite: Favourite)

    @Query("SELECT * from favourite ORDER BY username ASC")
    fun getAllFavourites(): LiveData<List<Favourite>>
}