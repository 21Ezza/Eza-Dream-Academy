package com.example.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.mvp.data.model.User
import com.example.mvp.databinding.ActivityDetailBinding

class Detail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val recipeLiveData = MutableLiveData<List<User>>(listOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firtName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val email = intent.getStringExtra("email")
        val avatar = intent.getStringExtra("avatar")


        binding.textView1.text = "First Name : $firtName\n" +
                "Last Name : $lastName\n" +
                "Email : $email" +
                "Avatar : $avatar"
        Glide.with(this)
            .load(avatar)
            .fitCenter()
            .placeholder(R.drawable.profile_picture)
            .into(binding.ivProfile)

        /*val imageView = binding.ivProfile
        Glide.with(imageView)
            .load("$avatar")
            .into(imageView)*/

    }
}