package com.example.githubuserapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.FragmentHomeBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Icon Badge
        val backgroundColor = ContextCompat.getColor(requireContext(), R.color.red)
        val toolbar = binding.toolbar
        val badgeDrawableMessage = BadgeDrawable.create(requireActivity()).apply {
            isVisible = true
            this.backgroundColor = backgroundColor
            number = 5
        }
        val badgeDrawableNotification = BadgeDrawable.create(requireActivity()).apply {
            isVisible = true
            this.backgroundColor = backgroundColor
            number = 5
        }
        BadgeUtils.attachBadgeDrawable(badgeDrawableMessage, toolbar, R.id.message_menu)
        BadgeUtils.attachBadgeDrawable(badgeDrawableNotification, toolbar, R.id.notification_menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}