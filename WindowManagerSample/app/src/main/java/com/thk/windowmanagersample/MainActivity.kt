package com.thk.windowmanagersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.window.layout.WindowInfoTracker
import com.thk.windowmanagersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  windowInfoTracker: WindowInfoTracker
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        windowInfoTracker = WindowInfoTracker.getOrCreate(this@MainActivity)
    }
}