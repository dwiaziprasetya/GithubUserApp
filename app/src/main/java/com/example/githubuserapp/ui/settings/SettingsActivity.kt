package com.example.githubuserapp.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserapp.MainViewModel
import com.example.githubuserapp.MainViewModelFactory
import com.example.githubuserapp.R
import com.example.githubuserapp.SettingPreferences
import com.example.githubuserapp.dataStore
import com.example.githubuserapp.databinding.ActivitySettingsBinding

@Suppress("DEPRECATION")
class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private var checkedItem = 0
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(application.dataStore)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(pref))[MainViewModel::class.java]

        mainViewModel.getThemeSetting().observe(this) {
            checkedItem = it
            when(it){
                0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }

        binding.btnTheme.setOnClickListener { showDialogAlertTheme() }
        binding.toolbarProfile.setOnClickListener { onBackPressed() }
    }

    private fun showDialogAlertTheme(){
        val builder = AlertDialog.Builder(this@SettingsActivity, R.style.CustomDialogTheme)
        builder
            .setTitle("Theme")
            .setNegativeButton("CANCEL") { _, _ -> }
            .setSingleChoiceItems(
                arrayOf(
                    "Light",
                    "Dark",
                    "Follow system"
                )
                , checkedItem) { dialog, which ->
                mainViewModel.saveThemeSetting(which)
                dialog.dismiss()
            }
            .create()
            .show()
    }
}