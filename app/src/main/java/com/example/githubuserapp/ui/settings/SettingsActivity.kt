package com.example.githubuserapp.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserapp.MainViewModel
import com.example.githubuserapp.SettingPreferences
import com.example.githubuserapp.ViewModelFactory
import com.example.githubuserapp.dataStore
import com.example.githubuserapp.databinding.ActivitySettingsBinding

@Suppress("DEPRECATION")
class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private var checkedItem = 0

    companion object {
        private const val KEY_CHECKED = "checked_item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(pref)
        )[MainViewModel::class.java]


        // Dark Mode (pending)
//        mainViewModel.getThemeSettings().observe(this) {
//            showDialogAlertTheme()
//        }
//
//        binding.btnTheme.setOnClickListener {
//            mainViewModel.saveThemeSetting(checkedItem)
//        }

        binding.toolbarProfile.setOnClickListener { onBackPressed() }
    }

    private fun showDialogAlertTheme(){
        val builder = AlertDialog.Builder(this@SettingsActivity)
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
                    checkedItem = which
                    when(which) {
                        0 -> {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        } 1 -> {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        } 2 -> {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                        }
                    }
                dialog.dismiss()
            }
            .create()
            .show()
    }
}