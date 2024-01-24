package com.example.githubuserapp.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuserapp.ui.followingFollower.followItems.FollowItemsFragment

class SectionsPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    var userName: String = ""

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowItemsFragment()
        when(position) {
            0 -> {
                fragment.arguments = Bundle().apply {
                    putString(FollowItemsFragment.ARG_NAME, userName)
                    putInt(FollowItemsFragment.SECTION_NUMBER, 0)
                }
            } 1 -> {
            fragment.arguments = Bundle().apply {
                putString(FollowItemsFragment.ARG_NAME, userName)
                putInt(FollowItemsFragment.SECTION_NUMBER, 1)
            }
            }
        }
        return fragment
    }
}