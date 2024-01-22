package com.example.githubuserapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.githubuserapp.databinding.FragmentDetailBinding
import com.example.githubuserapp.response.DetailResponse

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val person = DetailFragmentArgs.fromBundle(arguments as Bundle).data
        val detailViewModel = ViewModelProvider(this, DetailViewModelFactory(person))[DetailViewModel::class.java]

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
    }

    private fun setData(person : DetailResponse) {

        // default value
        fun TextView.setTextOrDash(value : CharSequence?) {
            text = value ?: "-"
        }

        Log.d("DetailFragment", "Person ID: ${person.id}")
        Log.d("DetailFragment", "Person Name: ${person.name}")
        Log.d("DetailFragment", "Person username: ${person.login}")
        Log.d("DetailFragment", "Person followers: ${person.followers}")
        Log.d("DetailFragment", "Person following: ${person.following}")
        Log.d("DetailFragment", "Person avatar: ${person.avatarUrl}")
        Log.d("DetailFragment", "Person email: ${person.email}")
        Log.d("DetailFragment", "Person repository: ${person.publicRepos}")
        Log.d("DetailFragment", "Person bio: ${person.bio}")
        Log.d("DetailFragment", "Person location: ${person.location}")

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
        Log.d("DetailFragment", "showLoading: $isLoading")
        binding.pbDetailPerson.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showConstraintLayout(isSetting: Boolean) {
        binding.cnsDetail.visibility = if (isSetting) View.VISIBLE else View.GONE
    }

    private fun handleNavigationClick(){
        findNavController().popBackStack()
    }
}