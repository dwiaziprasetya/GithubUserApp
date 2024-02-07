package com.example.githubuserapp.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.githubuserapp.data.local.entity.Favourite

class FavouriteDiffCallback(private val oldFavouriteList: List<Favourite>,
    private val newFavouriteList: List<Favourite>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldFavouriteList.size

    override fun getNewListSize(): Int = newFavouriteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFavouriteList[oldItemPosition].username == newFavouriteList[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFav = oldFavouriteList[oldItemPosition]
        val newFav = newFavouriteList[newItemPosition]
        return oldFav.avatarUrl == newFav.avatarUrl
    }
}