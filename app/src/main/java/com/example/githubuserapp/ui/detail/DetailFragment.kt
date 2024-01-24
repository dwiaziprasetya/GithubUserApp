package com.example.githubuserapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.FragmentDetailBinding
import com.example.githubuserapp.response.DetailResponse

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val USERNAME = "username"
        const val TAB_POSITION = "tab position"
        const val FOLLOWERS = 0
        const val FOLLOWING = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val personUsername = DetailFragmentArgs.fromBundle(arguments as Bundle).data
        val detailViewModel = ViewModelProvider(this, DetailViewModelFactory(personUsername))[DetailViewModel::class.java]

        detailViewModel.dataPerson.observe(viewLifecycleOwner) {
            setData(it)
        }

        detailViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        detailViewModel.isSetting.observe(viewLifecycleOwner) {
            showConstraintLayout(it)
        }

        binding.appBarProfile.setNavigationOnClickListener {
            handleNavigationClick()
        }

        // OnClick
        binding.tvFollowersCount.setOnClickListener {
            val mBundle = Bundle()
            mBundle.putString(USERNAME, personUsername)
            mBundle.putInt(TAB_POSITION, 0)
            view.findNavController().navigate(R.id.action_navigation_detail_to_navigation_following_follower, mBundle)
        }

        binding.tvFollowingCount.setOnClickListener {
            val mBundle = Bundle()
            mBundle.putString(USERNAME, personUsername)
            mBundle.putInt(TAB_POSITION, 1)
            view.findNavController().navigate(R.id.action_navigation_detail_to_navigation_following_follower, mBundle)
        }
    }

    private fun setData(person : DetailResponse) {

        // default value
        fun TextView.setTextOrDash(value : CharSequence?) {
            text = value ?: "-"
        }

        val imageUrl = "https://avatars.githubusercontent.com/u/"
        context?.let {
            Glide.with(it)
                .load(imageUrl + person.id + "?v=4")
                .into(binding.ciProfileImage)
        }
        binding.tvProfileName.setTextOrDash(person.name)
        binding.tvProfileUsername.setTextOrDash(person.login)
        binding.tvFollowersCount.setTextOrDash(person.followers.toString())
        binding.tvFollowingCount.setTextOrDash(person.following.toString())
        binding.tvUserBio.setTextOrDash(person.bio)
        binding.tvUserEmail.setTextOrDash(person.email)
        binding.tvUserLocation.setTextOrDash(person.location)
        binding.tvUserCompany.setTextOrDash(person.company)
        binding.tvUserRepository.setTextOrDash(person.publicRepos.toString())
    }

    private fun showLoading(isLoading : Boolean) {
        binding.pbDetailPerson.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showConstraintLayout(isSetting: Boolean) {
        binding.cnsDetail.visibility = if (isSetting) View.VISIBLE else View.GONE
    }

    private fun handleNavigationClick(){
        findNavController().popBackStack()
    }
}