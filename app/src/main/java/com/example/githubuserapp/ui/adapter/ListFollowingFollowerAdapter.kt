package com.example.githubuserapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.data.remote.response.FollowersResponseItem
import com.example.githubuserapp.databinding.ItemPersonListBinding
import com.example.githubuserapp.ui.followingFollower.FollowingFollowerFragmentDirections

class ListFollowingFollowerAdapter(private val list: ArrayList<FollowersResponseItem>): RecyclerView.Adapter<ListFollowingFollowerAdapter.ListViewHolder>() {
    class ListViewHolder(private val binding: ItemPersonListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(person: FollowersResponseItem){
            binding.tvPersonName.text = person.login
            binding.tvUserType.text = person.type
            Glide.with(itemView)
                .load(person.avatarUrl)
                .into(binding.tvPersonAvatar)
            binding.cardItem.setOnClickListener {
                val action = FollowingFollowerFragmentDirections.actionNavigationFollowingFollowerToNavigationDetail()
                action.data = person.login
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemPersonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }
}