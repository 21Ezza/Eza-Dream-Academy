package com.example.mvp.features.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvp.databinding.ActivityHomeBinding
import com.example.mvp.databinding.ActivityHomeBinding.inflate
import com.example.mvp.databinding.ActivityLayoutNotifBinding

class LayoutNotif : AppCompatActivity() {
    private lateinit var binding: ActivityLayoutNotifBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutNotifBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}