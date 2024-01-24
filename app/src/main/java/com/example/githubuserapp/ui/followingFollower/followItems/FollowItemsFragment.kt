package com.example.githubuserapp.ui.followingFollower.followItems

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.adapter.ListFollowingFollowerAdapter
import com.example.githubuserapp.databinding.FragmentFollowersBinding
import com.example.githubuserapp.response.FollowersResponseItem

class FollowItemsFragment : Fragment() {
    companion object {
        const val ARG_NAME = "username"
        const val SECTION_NUMBER = "section_number"
    }

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!

    private val list = ArrayList<FollowersResponseItem>()
    private val adapter = ListFollowingFollowerAdapter(list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username: String = arguments?.getString(ARG_NAME).toString()
        val index: Int = arguments?.getInt(SECTION_NUMBER, 0)!!
        Log.d("Followers Fragment", "username : $username")
        Log.d("Followers Fragment", "index : $index")
        val followItemsViewModel = ViewModelProvider(this, FollowItemsViewModelFactory(username, index))[FollowItemsViewModel::class.java]

        followItemsViewModel.dataPerson.observe(viewLifecycleOwner) {
            setData(it)
        }

        followItemsViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        binding.rvFollowers.hasFixedSize()
        binding.rvFollowers.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun setData(person: List<FollowersResponseItem>) {
        list.clear()
        list.addAll(person)
        binding.rvFollowers.adapter = adapter
        for (item in list) {
            Log.d("API_RESPONSE", "Item name: ${item.login}, Item Id: ${item.id}, " +
                    "Item avatarUrl: ${item.avatarUrl}, Item followersUrl: ${item.followersUrl}, " +
                    "Item followingUrl: ${item.followingUrl},Item gistsUrl: ${item.gistsUrl}")
        }
    }

    private fun showLoading(isLoading: Boolean){
        Log.d("FollowersFragment", "showLoading: $isLoading")
        binding.pbFollowers.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}