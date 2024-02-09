package com.example.githubuserapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.helper.FavouriteViewModelFactory
import com.example.githubuserapp.R
import com.example.githubuserapp.data.local.entity.Favourite
import com.example.githubuserapp.data.remote.response.DetailResponse
import com.example.githubuserapp.databinding.FragmentDetailBinding
import com.example.githubuserapp.ui.detail.add.AddFavouriteViewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var addFavouriteViewModel: AddFavouriteViewModel
    private lateinit var personUsername: String
    private var favourite: Favourite? = null
    private var checkFavourite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personUsername = DetailFragmentArgs.fromBundle(arguments as Bundle).data
        detailViewModel = ViewModelProvider(this, DetailViewModelFactory(personUsername))[DetailViewModel::class.java]
        addFavouriteViewModel = obtainViewModel(this@DetailFragment)

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

        addFavouriteViewModel.isUserFavourite(personUsername).observe(viewLifecycleOwner) { isFavourite ->
            checkFavourite = isFavourite
            if (isFavourite) {
                binding.appBarProfile.menu.findItem(R.id.favourite_menu).setIcon(R.drawable.icon_favourite_fill)
            }
        }

        binding.appBarProfile.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.share_menu -> {
                    val url = BuildConfig.BASE_URL_USER
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, url+personUsername)
                        type = "text/plain"
                    }
                    startActivity(Intent.createChooser(shareIntent, null))
                    true
                } R.id.favourite_menu -> {
                    checkFavourite = !checkFavourite
                    if (checkFavourite) {
                        // Add user
                        addFavouriteViewModel.insert(favourite as Favourite)
                        Toast.makeText(requireActivity(), "$personUsername added to favourites",
                            Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        menuItem.setIcon(R.drawable.icon_favourite)
                        // delete user
                        addFavouriteViewModel.delete(favourite as Favourite)
                        Toast.makeText(requireActivity(), "$personUsername removed from favourites",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                    true
                } else -> false
            }
        }
    }

    private fun setData(person : DetailResponse) {
        // default value
        fun TextView.setTextOrDash(value : CharSequence?) {
            text = value ?: "-"
        }

        favourite = Favourite(person.login, person.avatarUrl)


        val imageUrl = BuildConfig.BASE_URL_AVATAR_URL

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

    private fun obtainViewModel(fragment: Fragment): AddFavouriteViewModel {
        val factory = FavouriteViewModelFactory.getInstance(fragment.requireActivity().application)
        return ViewModelProvider(fragment, factory)[(AddFavouriteViewModel::class.java)]
    }

    companion object {
        const val USERNAME = "username"
        const val TAB_POSITION = "tab position"
    }
}