package com.example.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.databinding.ItemPersonListBinding
import com.example.githubuserapp.databinding.SearchPersonBinding
import com.example.githubuserapp.response.ItemsItem

class SearchAdapter (private val listPerson : ArrayList<ItemsItem>) : RecyclerView.Adapter<SearchAdapter.ListViewHolder>() {

//     val listPerson: ArrayList<ItemsItem>

    class ListViewHolder(private val binding: ItemPersonListBinding) : RecyclerView.ViewHolder(binding.root){
        val imageUrl = "https://avatars.githubusercontent.com/u/"
        fun bind(person : ItemsItem){
            binding.tvPersonName.text = person.login
            Glide.with(itemView)
                .load(imageUrl + person.id + "?v=4")
                .into(binding.tvPersonAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemPersonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listPerson.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        listPerson[position].let {
            holder.bind(it)
        }
    }

}