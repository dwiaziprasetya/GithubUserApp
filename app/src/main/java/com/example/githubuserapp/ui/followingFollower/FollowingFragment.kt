package com.example.githubuserapp.ui.followingFollower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubuserapp.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {

    private var _binding : FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString(ARG_NAME)
        binding.text2.text = "Ini Following $username"
    }

    companion object {
        const val ARG_NAME = "section_number"
    }
}