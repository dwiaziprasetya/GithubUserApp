package com.example.githubuserapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuserapp.databinding.FragmentProfileBinding
import com.example.githubuserapp.response.DetailResponse

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

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

        val profileViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ProfileViewModel::class.java]

        profileViewModel.dataProfilePerson.observe(viewLifecycleOwner) {
            setData(it)
        }

        profileViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        profileViewModel.isSetting.observe(viewLifecycleOwner) {
            showConstraintLayout(it)
        }
    }

    private fun setData(person: DetailResponse){
        fun TextView.setTextOrDash(value: CharSequence?){
            text = value ?: "-"
        }

        val imageUrl = "https://avatars.githubusercontent.com/u/"
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
}