package com.example.githubuserapp.ui.profile

import androidx.lifecycle.ViewModel
import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.data.repository.ProfileRepository

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

    val dataProfilePerson = profileRepository.dataProfilePerson
    val isLoading = profileRepository.isLoading
    val isSetting = profileRepository.isSetting

    init {
        setProfileData()
    }

    fun setProfileData() {
        profileRepository.setProfileData(USER)
    }

    companion object {
        var USER = BuildConfig.PROFILE_USERNAME
    }
}