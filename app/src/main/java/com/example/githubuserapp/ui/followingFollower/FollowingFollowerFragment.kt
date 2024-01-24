package com.example.githubuserapp.ui.followingFollower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.githubuserapp.R
import com.example.githubuserapp.adapter.SectionsPagerAdapter
import com.example.githubuserapp.databinding.FragmentFollowingFollowerBinding
import com.example.githubuserapp.ui.detail.DetailFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FollowingFollowerFragment : Fragment() {

    private var _binding: FragmentFollowingFollowerBinding? = null
    private val binding get() = _binding!!

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers_template,
            R.string.following_template
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFollowingFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Data sharing
        val tabPosition = arguments?.getInt(DetailFragment.TAB_POSITION)
        val personUsername = arguments?.getString(DetailFragment.USERNAME)

        // Tab Layout and View Pager 2
        val sectionsPageAdapter = SectionsPagerAdapter(requireActivity())
        sectionsPageAdapter.userName = personUsername!!
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPageAdapter
        viewPager.post { viewPager.setCurrentItem(tabPosition!!, false) }
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        binding.appBarFollowingFollowers.setNavigationOnClickListener { handleNavigationClick() }
        binding.appBarFollowingFollowers.setTitle(personUsername)
    }

    private fun handleNavigationClick(){
        findNavController().popBackStack()
    }
}