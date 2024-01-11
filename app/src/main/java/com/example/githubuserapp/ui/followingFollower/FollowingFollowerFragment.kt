package com.example.githubuserapp.ui.followingFollower

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubuserapp.R

class FollowingFollowerFragment : Fragment() {

    companion object {
        fun newInstance() = FollowingFollowerFragment()
    }

    private lateinit var viewModel: FollowingFollowerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_following_follower, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FollowingFollowerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}