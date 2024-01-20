@file:Suppress("DEPRECATION")

package com.example.githubuserapp.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.FragmentDetailBinding
import com.example.githubuserapp.response.DetailResponse
import com.example.githubuserapp.response.ItemsItem
import com.example.githubuserapp.retrofit.ApiConfig
import com.example.githubuserapp.ui.search.SearchFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val personUsername = DetailFragmentArgs.fromBundle(arguments as Bundle).data
        setPersonData(personUsername)

        binding.appBarProfile.setNavigationOnClickListener {
            handleNavigationClick()
        }


        super.onViewCreated(view, savedInstanceState)
    }

    private fun setPersonData(username : String) {
        val client = ApiConfig.getApiService().getDetailPerson(username)
        Log.d("NAMA USERNAME WOYY", "showusername: ")
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful) {
                    showLoading(false)
                    val person = response.body()
                    if (person != null) {
                        setData(person)
                    }
                    showConstraintLayout(true)
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                showLoading(false)
            }
        })
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
        binding.tvUserOrganization.setTextOrDash(person.company)
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