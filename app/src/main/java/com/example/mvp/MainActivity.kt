package com.example.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profileCard.setTopTest("Hi, Viola Maulatan")
        binding.profileCard.bootomText = "150 Point"

        binding.profileCard.setTextColor(topColorId = R.color.black)
        binding.profileCard.setTextColor(bottomColorId = R.color.white)


    }
}