package com.example.mvp.features.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvp.databinding.ActivityDetailBinding

class Detail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val accountDetail = intent.getStringExtra("accountDetail")
        val transactionAmount = intent.getStringExtra("transactionAmount")

        binding.textView1.text = "$accountDetail\n$transactionAmount"
    }
}