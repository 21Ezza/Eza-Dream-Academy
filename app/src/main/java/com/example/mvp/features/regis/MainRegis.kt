package com.example.mvp.features.regis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.mvp.databinding.ActivityMainRegisBinding
import com.example.mvp.features.MainActivity

class MainRegis : AppCompatActivity(), RegisView {
    lateinit var binding: ActivityMainRegisBinding
    private val presenter = RegisPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRegisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.onAttach(this)

        binding.btnSelanjutnya.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onLoading() {
        binding.progressIndicator.isVisible = true
    }

    override fun onFinishedLoading() {
        binding.progressIndicator.isVisible = true
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessLogin() {
        TODO("Not yet implemented")
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}