package com.example.githubuserapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.data.local.entity.Favourite
import com.example.githubuserapp.databinding.ItemFavouriteBinding
import com.example.githubuserapp.helper.FavouriteDiffCallback
import com.example.githubuserapp.ui.favourite.FavouriteFragmentDirections

class FavouriteAdapter: RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {
    private val listFavourites = ArrayList<Favourite>()

    fun setListFavourite(listFavourite: List<Favourite>){
        val diffCallback = FavouriteDiffCallback(this.listFavourites, listFavourite)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavourites.clear()
        this.listFavourites.addAll(listFavourite)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class FavouriteViewHolder(private val binding: ItemFavouriteBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun bind(favourite: Favourite) {
            with (binding) {
                tvPersonName.text = favourite.username
                Glide.with(itemView)
                    .load(favourite.avatarUrl)
                    .into(tvPersonAvatar)
                cardItem.setOnClickListener {
                    val action = FavouriteFragmentDirections.actionFavouriteFragmentToNavigationDetail()
                    action.data = favourite.username
                    it.findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val binding = ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun getItemCount(): Int = listFavourites.size

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(listFavourites[position])
    }
}