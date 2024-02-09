package com.example.githubuserapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.R
import com.example.githubuserapp.data.local.entity.Favourite
import com.example.githubuserapp.data.remote.response.DetailResponse
import com.example.githubuserapp.databinding.FragmentProfileBinding
import com.example.githubuserapp.helper.FavouriteViewModelFactory
import com.example.githubuserapp.ui.favourite.FavouriteViewModel
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils

@ExperimentalBadgeUtils class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var favourite: Favourite

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favourite = Favourite()
        val profileViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ProfileViewModel::class.java]
        val itemFavourite = obtainViewModel(this@ProfileFragment)

        itemFavourite.getAllFavourites().observe(viewLifecycleOwner) {
            Log.d("Jumlah Item", "${it.size}")
            setBadgeIconFavourite(it.size)
        }



        profileViewModel.dataProfilePerson.observe(viewLifecycleOwner) {
            setData(it)
        }

        profileViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        profileViewModel.isSetting.observe(viewLifecycleOwner) {
            showConstraintLayout(it)
        }

        binding.tvFollowersCount.setOnClickListener {
            val mBundle = Bundle()
            mBundle.putString(USERNAME, BuildConfig.PROFILE_USERNAME)
            mBundle.putInt(TAB_POSITION, FOLLOWERS)
            view.findNavController().navigate(R.id.action_navigation_profile_to_navigation_following_follower, mBundle)
        }

        binding.tvFollowingCount.setOnClickListener {
            val mBundle = Bundle()
            mBundle.putString(USERNAME, BuildConfig.PROFILE_USERNAME)
            mBundle.putInt(TAB_POSITION, FOLLOWING)
            view.findNavController().navigate(R.id.action_navigation_profile_to_navigation_following_follower, mBundle)
        }

        binding.toolbarProfile.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.settings_menu -> {
                    view.findNavController().navigate(R.id.action_navigation_profile_to_settingsActivity)
                    true
                } R.id.favourite_menu-> {
                    view.findNavController().navigate(R.id.action_navigation_profile_to_favouriteFragment)
                    true
                } R.id.share_menu -> {
                    val url = BuildConfig.BASE_URL_USER
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, url+BuildConfig.PROFILE_USERNAME)
                        type = "text/plain"
                    }
                    startActivity(Intent.createChooser(shareIntent, null))
                    true
                } else -> false
            }
        }
    }

    private fun setData(person: DetailResponse){

        // Default value
        fun TextView.setTextOrDash(value: CharSequence?){
            text = value ?: "-"
        }

        val imageUrl = BuildConfig.BASE_URL_AVATAR_URL
        context?.let {
            Glide.with(it)
                .load(imageUrl + person.id + "?v=4")
                .into(binding.ciProfileImage)
        }

        with(binding){
            tvProfileName.setTextOrDash(person.name)
            tvProfileUsername.setTextOrDash(person.login)
            tvFollowersCount.setTextOrDash(person.followers.toString())
            tvFollowingCount.setTextOrDash(person.following.toString())
            tvUserBio.setTextOrDash(person.bio)
            tvUserEmail.setTextOrDash(person.email)
            tvUserLocation.setTextOrDash(person.location)
            tvUserCompany.setTextOrDash(person.company)
            tvUserRepository.setTextOrDash(person.publicRepos.toString())
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbProfilePerson.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showConstraintLayout(isSetting: Boolean) {
        binding.cnsProfile.visibility = if (isSetting) View.VISIBLE else View.GONE
    }

    private fun obtainViewModel(fragment: Fragment): FavouriteViewModel {
        val factory = FavouriteViewModelFactory.getInstance(fragment.requireActivity().application)
        return ViewModelProvider(fragment, factory)[(FavouriteViewModel::class.java)]
    }

    private fun setBadgeIconFavourite(number: Int){
        val backgroundColor = ContextCompat.getColor(requireContext(), R.color.red)
        val toolbar = binding.toolbarProfile
        val badgeDrawable = BadgeDrawable.create(requireActivity()).apply {
            isVisible = true
            badgeTextColor = ContextCompat.getColor(requireContext(), R.color.white)
            this.backgroundColor = backgroundColor
            this.number = number
        }
        BadgeUtils.attachBadgeDrawable(badgeDrawable, toolbar, R.id.favourite_menu)
    }

    companion object {
        const val USERNAME = "username"
        const val TAB_POSITION = "tab position"
        const val FOLLOWERS = 0
        const val FOLLOWING = 1
    }
}