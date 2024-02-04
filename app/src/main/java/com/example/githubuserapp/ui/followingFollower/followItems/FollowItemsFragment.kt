package com.example.githubuserapp.ui.followingFollower.followItems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.ui.adapter.ListFollowingFollowerAdapter
import com.example.githubuserapp.databinding.FragmentFollowersBinding
import com.example.githubuserapp.data.remote.response.FollowersResponseItem

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
    ): View {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username: String = arguments?.getString(ARG_NAME).toString()
        val index: Int = arguments?.getInt(SECTION_NUMBER, 0) as Int

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
    }

    private fun showLoading(isLoading: Boolean){
        binding.pbFollowers.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}