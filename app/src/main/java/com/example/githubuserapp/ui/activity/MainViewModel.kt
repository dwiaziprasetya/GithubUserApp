package com.example.githubuserapp.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.utils.SettingPreferences
import kotlinx.coroutines.launch

class MainViewModel(private val pref: SettingPreferences): ViewModel() {
    fun getThemeSetting(): LiveData<Int> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(checkItem: Int){
        viewModelScope.launch {
            pref.saveThemeSetting(checkItem)
        }
    }
}