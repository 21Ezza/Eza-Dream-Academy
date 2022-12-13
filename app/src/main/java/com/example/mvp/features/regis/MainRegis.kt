package com.example.mvp.features.regis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvp.databinding.ActivityMainRegisBinding
import com.example.mvp.features.MainActivity

class MainRegis : AppCompatActivity() {
    lateinit var binding : ActivityMainRegisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainRegisBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}