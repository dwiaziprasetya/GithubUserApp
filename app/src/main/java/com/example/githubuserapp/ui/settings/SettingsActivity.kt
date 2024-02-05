package com.example.githubuserapp.ui.settings

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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

        if (savedInstanceState != null){
            checkedItem = savedInstanceState.getInt(KEY_CHECKED)
        }

        binding.btnTheme.setOnClickListener {
            showDialogAlertTheme()
        }
        binding.toolbarProfile.setOnClickListener { onBackPressed() }
    }

    private fun showDialogAlertTheme(){
        Log.d("AlertDialog", "checked item = $checkedItem")
        val builder = AlertDialog.Builder(this@SettingsActivity)
        builder
            .setTitle("Theme")
            .setNegativeButton("CANCEL") { _, _ -> }
            .setSingleChoiceItems(
                arrayOf(
                    "Light",
                    "Dark"
                )
                , checkedItem) { dialog, which ->
                checkedItem = which
                    when(which) {
                        1 -> {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        }
                        0 -> {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        }
                    }
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_CHECKED, checkedItem)
        super.onSaveInstanceState(outState)
    }
}