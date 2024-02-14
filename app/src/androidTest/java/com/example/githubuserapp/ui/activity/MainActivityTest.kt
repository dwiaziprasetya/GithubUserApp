package com.example.githubuserapp.ui.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    fun assert(){
        
    }
}