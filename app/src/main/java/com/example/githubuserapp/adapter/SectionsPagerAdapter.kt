package com.example.githubuserapp.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuserapp.ui.followingFollower.followers.FollowersFragment
import com.example.githubuserapp.ui.followingFollower.following.FollowingFragment

class SectionsPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    var userName: String = ""

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        var fragment: Fragment? = null

        when(position) {
            0 -> {
                fragment = FollowersFragment()
                fragment.arguments = Bundle().apply {
                    putString(FollowersFragment.ARG_NAME, userName)
                }
            }
            1 -> {
                fragment = FollowingFragment()
                fragment.arguments = Bundle().apply {
                    putString(FollowingFragment.ARG_NAME, userName)
                }
            }
        }

        return fragment as Fragment
    }

}