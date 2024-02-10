package com.example.githubuserapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.FragmentHomeBinding

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
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.plus_menu -> {
                    Toast
                        .makeText(
                            requireActivity(),
                            "developers has not updated this feature",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                    true
                } R.id.message_menu -> {
                    Toast
                        .makeText(requireActivity(),
                            "developers has not updated this feature",
                            Toast.LENGTH_SHORT)
                        .show()
                    true
                } else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}