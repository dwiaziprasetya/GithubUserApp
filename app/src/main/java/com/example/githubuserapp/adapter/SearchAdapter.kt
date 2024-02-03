package com.example.githubuserapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.databinding.ItemPersonListBinding
import com.example.githubuserapp.response.ItemsItem

class SearchAdapter (private val listPerson : ArrayList<ItemsItem>) : RecyclerView.Adapter<SearchAdapter.ListViewHolder>() {

    private lateinit var onItemCallback : OnItemClickCallBack

    class ListViewHolder(val binding: ItemPersonListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person : ItemsItem){
            val baseUrl = BuildConfig.BASE_URL_AVATAR_URL
            binding.tvPersonName.text = person.login
            binding.tvUserType.text = person.type
            Glide.with(itemView)
                .load(baseUrl + person.id + "?v=4")
                .into(binding.tvPersonAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemPersonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listPerson.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listPerson[position]
        holder.bind(data)
        holder.binding.root.setOnClickListener {
            onItemCallback.onItemClicked(data)
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack) {
        this.onItemCallback = onItemClickCallback
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data : ItemsItem)
    }
}