package com.example.githubuserapp.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuserapp.databinding.ActivitySettingsBinding

@Suppress("DEPRECATION")
class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarProfile.setOnClickListener { onBackPressed() }
    }
}